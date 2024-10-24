package com.te.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.te.ems.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmpEmail(String empEmail);
	
	Optional<Employee> findByEmpId(Integer empId);
	
	
}
