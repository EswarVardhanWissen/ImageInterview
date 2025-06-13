package org.example.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name ="CATAGORY")
@Data
@Builder
public class Catagory {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE_STATUS")
    private Boolean activeStatus;

    @ManyToOne
    @JoinColumn(name = "CATAGORY_ID", nullable = false)
    private Catagory catagory;

}
