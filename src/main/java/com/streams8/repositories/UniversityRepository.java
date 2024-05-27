package com.streams8.repositories;

import com.streams8.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {
    Optional<University> findByUniversityNameAndUniversityRegNumber (String universityName, String universityRegNumber);
}
