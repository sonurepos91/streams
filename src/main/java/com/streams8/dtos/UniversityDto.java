package com.streams8.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UniversityDto {

    private String universityName;
    private String universityRegNumber;
    private String state;
    private Integer yearOfEstablishment;
    private List<CollegeDto> colleges = new ArrayList<>();
}
