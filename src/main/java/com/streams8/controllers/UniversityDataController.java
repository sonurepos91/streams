package com.streams8.controllers;

import com.streams8.dtos.UniversityDto;
import com.streams8.service.UniversityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/university")
public class UniversityDataController<T> {

    Logger log = LogManager.getLogger(UniversityDataController.class);

    @Autowired
    private UniversityService universityService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> registerUniversity(@RequestBody UniversityDto universityDto){
        log.info(" :::::::::::::::::::::  University Registration Process Initiated :::::::::::::::::::::::::::::::  ");
        universityService.validate(universityDto);
        Long registeredId = universityService.register(universityDto);
        return new ResponseEntity<>((T)registeredId, HttpStatus.CREATED);
    }

    @GetMapping(value="/{parameter}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> groupCollegesByGivenParameter(@PathVariable String parameter){
        log.info(" :::::::::::::::: Grouping By " + parameter + " ::::::::: initiated");
        return new ResponseEntity<>((T)universityService.groupByParameter(parameter),HttpStatus.OK);
    }
}
