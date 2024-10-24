package com.te.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ems.entity.EducationalDetails;

@Repository
public interface EducationalDetailsRepository extends JpaRepository<EducationalDetails, Integer>{

}
