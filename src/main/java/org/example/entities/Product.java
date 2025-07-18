package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "PRODUCT")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "CATAGORY_ID", nullable = false)
    private Integer catagoryId;

    @ManyToOne
    @JoinColumn(name = "CATAGORY_ID", insertable = false, updatable = false, nullable = false)
    private Catagory catagory;


}


