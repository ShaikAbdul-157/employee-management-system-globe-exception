package com.te.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ems.entity.SecondaryInfo;

@Repository
public interface SecondaryInfoRepository extends JpaRepository<SecondaryInfo, String> {

}
