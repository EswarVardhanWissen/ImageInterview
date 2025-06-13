package org.example.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="CATAGORY")
@Getter
@Setter
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



}
