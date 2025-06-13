package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.entities.Catagory;
import org.example.repository.CatagoryRepository;
import org.example.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CatagoryController {

    private final CatagoryRepository catagoryRepository;
    private final ProductRepository productRepository;

    @GetMapping("/catagories")
    public ResponseEntity<List<Catagory>> getAllCatagories() {
        List<Catagory> listOfCatagories = catagoryRepository.findAll();
        return ResponseEntity.ok(listOfCatagories);
    }

    @GetMapping("/catagories/{id}")
    public ResponseEntity<Catagory> getCatagoryByID(@PathVariable Integer id) {
        Catagory getCatagoryByID = catagoryRepository.findById(id).get();
        return ResponseEntity.ok(getCatagoryByID);
    }

    @PostMapping("/catagories")
    public ResponseEntity<String> createCatagory(@RequestBody Catagory catagory) {
        Catagory newCatagory = Catagory.builder()
                .name(catagory.getName())
                .description(catagory.getDescription())
                .activeStatus(catagory.getActiveStatus())
                .build();
        catagoryRepository.save(newCatagory);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("catagories/{id}")
    public ResponseEntity<String> deleteCatagory(@PathVariable Integer id) {
        if(productRepository.countByCatagoryId(id) > 0) {
            return ResponseEntity.badRequest().body("Cannot delete category with existing products.");
        }
        catagoryRepository.deleteById(id);
        return ResponseEntity.ok("Successfully Deleted");
    }

}
