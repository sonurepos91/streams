package com.streams8.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Employee {
    private Integer empId;
    private String name;
    private Integer salary;

    private String Department;

}
