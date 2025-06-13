package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.entities.Product;
import org.example.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ExcelController {

    private final ProductRepository productRepository;

    @PostMapping("/import")
    public ResponseEntity<String> importProducts(@RequestBody MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                Product product = Product.builder()
                        .skuCode(row.getCell(0).getStringCellValue())
                        .productName(row.getCell(1).getStringCellValue())
                        .description(row.getCell(2).getStringCellValue())
                        .price(row.getCell(3).getNumericCellValue())
                        .stockQuantity((int) row.getCell(4).getNumericCellValue())
                        .status((int) row.getCell(5).getNumericCellValue())
                        .build();

                productRepository.save(product);
                return ResponseEntity.ok("Products imported successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("Failed to import products.");
    }

    @GetMapping("/template")
    public ResponseEntity exportProducts() {
        try {
            List<Product> products = productRepository.findAll();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Products");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("SKU Code");
            headerRow.createCell(1).setCellValue("Product Name");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Price");
            headerRow.createCell(4).setCellValue("Stock Quantity");
            headerRow.createCell(5).setCellValue("Status");

            int rowIndex = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(product.getSkuCode());
                row.createCell(1).setCellValue(product.getProductName());
                row.createCell(2).setCellValue(product.getDescription());
                row.createCell(3).setCellValue(product.getPrice());
                row.createCell(4).setCellValue(product.getStockQuantity());
                row.createCell(5).setCellValue(product.getStatus());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                workbook.close();

                return ResponseEntity.ok()
                        .body(out.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to export products.");
        }
    }

}