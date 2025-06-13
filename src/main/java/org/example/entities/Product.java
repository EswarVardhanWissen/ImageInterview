package org.example.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
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
    private int stockQuantity;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CATAGORY_ID")
    private int catagoryId;

    //TODO: Mapping to be done

}


