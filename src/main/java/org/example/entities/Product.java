package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "PRODUCT")
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "SKU_CODE")
    private String skuCode;

    @Column(name= "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name ="PRICE")
    private double price;

    @Column(name = "STOCK_QUANTITY")
    @PositiveOrZero
    private int stockQuantity;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CATAGORY_ID")
    private int catagoryId;


}


