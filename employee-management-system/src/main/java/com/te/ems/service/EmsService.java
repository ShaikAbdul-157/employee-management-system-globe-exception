package com.te.ems.service;

import java.util.Optional;

import com.te.ems.dto.EmployeeDto;
import com.te.ems.dto.ProjectDetailsDto;
import com.te.ems.dto.RegistrationDto;
import com.te.ems.dto.UpdateEmployeeDto;
import com.te.ems.entity.Employee;

public interface EmsService {

	Optional<Integer> registration(RegistrationDto registrationDto);

	Boolean setProjectDetails(String empEmail, ProjectDetailsDto projectDetailsDto);

	Optional<EmployeeDto> getEmployee(Integer empId);

}
