package com.streams8.service;

import com.streams8.dtos.UniversityDto;
import com.streams8.models.University;

import java.util.List;
import java.util.Map;

public interface UniversityService {
    void validate (UniversityDto universityDto);

    Long register (UniversityDto universityDto);

    Map<String,List<University>> groupByParameter (String parameter);
}
