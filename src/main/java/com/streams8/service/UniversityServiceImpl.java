package com.streams8.service;

import com.streams8.dtos.CollegeDto;
import com.streams8.dtos.UniversityDto;
import com.streams8.exception.DuplicateCollegeInfoException;
import com.streams8.exception.UniversityAlreadyRegisteredException;
import com.streams8.models.College;
import com.streams8.models.University;
import com.streams8.repositories.CollegeRepository;
import com.streams8.repositories.UniversityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    @Transactional(readOnly = true)
    public void validate (UniversityDto universityDto) {

        if (StringUtils.isNotBlank(universityDto.getUniversityName()) && StringUtils.isNotBlank(universityDto.getUniversityRegNumber())) {
            Optional<University> entity = universityRepository.
                    findByUniversityNameAndUniversityRegNumber(universityDto.getUniversityName(), universityDto.getUniversityRegNumber());
            if(entity.isPresent()){
                    throw new UniversityAlreadyRegisteredException("universityName",universityDto.getUniversityName(),"");
            }
        }
        if(universityDto.getColleges()!=null){
            universityDto.getColleges().forEach(college->{
                Optional<College> collegeList = collegeRepository.findAll().stream().
                        filter(e -> e.getName().equals(college.getName()) ||
                                e.getRegNumber().equals(college.getRegNumber())).findFirst();
              //  System.out.println(collegeList.get(0).getName());
                if(collegeList.isPresent()){
                    throw  new DuplicateCollegeInfoException("College Name ",college.getName(),"");
                }
            });
        }
    }

    @Override
    @Transactional()
    public Long register (UniversityDto universityDto) {
        Long universityId = saveUniversity(universityDto);
        if(universityDto.getColleges() != null){
            saveAffilatedColleges(universityId,universityDto.getColleges());
        }
        return universityId;
    }

    private Long saveUniversity (UniversityDto universityDto) {
        University university = new University();
        university.setUniversityName(universityDto.getUniversityName());
        university.setUniversityRegNumber(universityDto.getUniversityRegNumber());
        university.setState(universityDto.getState());
        university.setYearOfEstablishment(universityDto.getYearOfEstablishment());
        return universityRepository.save(university).getId();
    }
    private void saveAffilatedColleges (Long universityId, List<CollegeDto> collegeDtos) {
        List<College> collegeList = new ArrayList<>();
        collegeDtos.forEach(collegeDto -> {
            College college = new College();
            college.setName(collegeDto.getName());
            college.setRegNumber(collegeDto.getRegNumber());
            college.setUniversityId(universityId);
            college.setState(collegeDto.getState());
            collegeList.add(college);
        });
        collegeRepository.saveAll(collegeList);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String,List<University>> groupByParameter (String parameter) {
        if(parameter.equals("getUniversityName"))
        return universityRepository.findAll().stream()
                .collect(Collectors.groupingBy(University::getUniversityName));
        else{
            return universityRepository.findAll().stream()
                    .collect(Collectors.groupingBy(University::getState));
        }
    }
}
