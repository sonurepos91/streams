package com.streams8.repositories;

import com.streams8.models.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College,Long> {
    College findByName (String s);

    College findByRegNumber (String s);
}
