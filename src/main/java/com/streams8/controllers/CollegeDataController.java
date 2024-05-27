package com.streams8.controllers;

import com.streams8.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/college")
public class CollegeDataController<T> {

    @Autowired
    private CollegeService collegeService;

    @GetMapping(path = "/")
    public ResponseEntity<T> getData(){
        return collegeService.playWithStreams();
    };
}
