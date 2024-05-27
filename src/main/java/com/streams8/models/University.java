package com.streams8.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Column(name="university_name",nullable = false,unique = true)
    private String universityName;

    @Column(name="university_reg_number",nullable = false,unique = true)
    private String universityRegNumber;

    @Column(name="state",nullable = false)
    private String state;

    @Column(name="year_of_establishment",nullable = false)
    private Integer yearOfEstablishment;

    @OneToMany(mappedBy = "universityId", cascade = CascadeType.ALL)
    private List<College> colleges = new ArrayList<>();

}
