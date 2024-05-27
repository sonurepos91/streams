package com.streams8.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "college")
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "reg_number", nullable = false, unique = true)
    private String regNumber;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "university_id", nullable = false)
    private Long universityId;

}

