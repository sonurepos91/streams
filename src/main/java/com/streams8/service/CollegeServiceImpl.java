package com.streams8.service;

import com.streams8.exception.NoCollegeInStateException;
import com.streams8.exception.NoSuchCollegeException;
import com.streams8.models.College;
import com.streams8.models.University;
import com.streams8.repositories.CollegeRepository;
import com.streams8.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CollegeServiceImpl<T> implements CollegeService{
    
    
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UniversityRepository universityRepository;
    @Override
    public ResponseEntity<T> playWithStreams () {

        // Find All the Colleges Info With Given Names

        String[] collegeNames = {"Stephen College","Kokila Behen College"};

        // Map
        List<College> collegeList = Arrays.asList(collegeNames).stream()
                .map(collegeRepository::findByName)
                .limit(1)
                .collect(Collectors.toList());

        String[] registryNo = {"DUK1-901287","KA789034","KM789034","HM789039"};

        //Map
        List<College> collegeStream = Stream.of(registryNo)
                .map(collegeRepository::findByRegNumber)
                .collect(Collectors.toList());

        //Group By Colleges In State
        Map<String, List<College>> collegeByState = collegeRepository.findAll().stream()
                .collect(Collectors.groupingBy(College::getState));

        // Iterate MapBy ForEach : BiConsumer
        collegeByState.forEach((key,value) -> System.out.println(key+ " : " + value));

        // Group By Universities
        Map<String, List<University>> groupByUniversity = universityRepository.findAll().stream()
                .collect(Collectors.groupingBy(University::getUniversityName));

        // Flat Map List<List<Universities> to List<Delhi Universities>
        List<University> indraprasthUniversity = Arrays.asList(groupByUniversity.get("Indraprasth University")).stream()
                .flatMap(Collection::stream).collect(Collectors.toList());

        List<University> delhiUniversities = Arrays.asList(groupByUniversity.get("Delhi University")).stream()
                .flatMap(universities -> universities.stream()).collect(Collectors.toList());





        College college = Arrays.asList(registryNo).stream().map(collegeRepository::findByRegNumber)
                .filter((e) -> e != null)
                .filter((e) -> e.getRegNumber().equals("DUK1-901287"))
                .findFirst()
                .orElseThrow(()-> new NoSuchCollegeException("regNumber","DUK1-901287",""));

        College college1 = collegeRepository.findAll().stream().filter(e -> e.getState().equals("UP"))
                .findAny()
                .filter(e->e.getName()!=null)
                .orElseThrow(() -> new NoCollegeInStateException("state", "UP", ""));
        
        // Sort University By University Name :
        // Note Internally .sorted(Comparator.comparingInt(University::getYearOfEstablishment)
        // also calls the compareTo API only same as we do compareTo
        List<University> sortedByName = universityRepository.findAll().stream()
                .sorted((e1, e2) -> e1.getUniversityName().compareToIgnoreCase(e2.getUniversityName()))
                .collect(Collectors.toList());

        List<University> sortByYear = universityRepository.findAll().stream()
                .sorted(Comparator.comparingInt(University::getYearOfEstablishment))
                .collect(Collectors.toList());


        return new ResponseEntity<>((T) sortedByName, HttpStatus.OK);


    }
}
