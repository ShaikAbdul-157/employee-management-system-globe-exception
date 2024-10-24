package com.te.ems.service.implementation;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.ems.dto.EducationalDetailsDto;
import com.te.ems.dto.EmployeeDto;
import com.te.ems.dto.ProjectDetailsDto;
import com.te.ems.dto.RegistrationDto;
import com.te.ems.dto.SecondaryInfoDto;
import com.te.ems.entity.EducationalDetails;
import com.te.ems.entity.Employee;
import com.te.ems.entity.ProjectDetails;
import com.te.ems.entity.SecondaryInfo;
import com.te.ems.repository.EmployeeRepository;
import com.te.ems.repository.ProjectDetailsRepository;
import com.te.ems.service.EmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmsServiceImpl implements EmsService {

	private final EmployeeRepository employeeRepository;
	private final ProjectDetailsRepository projectDetailsRepository;

	@Override
	public Optional<Integer> registration(RegistrationDto registrationDto) {
		log.info("EmsServiceImpl:registration Logic for registration service started {}", registrationDto);
		Employee employee = new Employee();
		SecondaryInfo secondaryInfo = new SecondaryInfo();
		List<EducationalDetails> educationalDetails = Lists.newArrayList();

		log.debug("EmsServiceImpl:registration dto to bean conversion");
		BeanUtils.copyProperties(registrationDto, employee);
		BeanUtils.copyProperties(registrationDto.getSecondaryInfo(), secondaryInfo);
		// BeanUtils.copyProperties(registrationDto.getEducationalDetails(),
		// educationalDetails);

		for (EducationalDetailsDto educationalDetailsDto : registrationDto.getEducationalDetails()) {
			EducationalDetails educationalDetailsEntity = new EducationalDetails();
			BeanUtils.copyProperties(educationalDetailsDto, educationalDetailsEntity);
			educationalDetails.add(educationalDetailsEntity);
		}
		// setting objects to each other due to bi-directional mapping
		employee.setSecondaryInfo(secondaryInfo);
		secondaryInfo.setEmployee(employee);

		employee.setEducationalDetails(educationalDetails);
		for (EducationalDetails educationalDetails2 : educationalDetails) {
			educationalDetails2.setEmployee(employee);
		}

		log.info("EmsServiceImpl:registration returning the response after calling repository ");
		return Optional.ofNullable(employeeRepository.save(employee).getEmpId());
	}


	@Override
	public Boolean setProjectDetails(String empEmail, ProjectDetailsDto projectDetailsDto) {
		log.info("EmsServiceImpl:setProjectDetails started execution");
		log.debug("EmsServiceImpl:setProjectDetails tryig to find employee by employee email");
		Optional<Employee> optionalEmployee = employeeRepository.findByEmpEmail(empEmail);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			log.debug("EmsServiceImpl:setProjectDetails employee is present,{}",employee);
			ProjectDetails projectDetails = new ProjectDetails();
			BeanUtils.copyProperties(projectDetailsDto, projectDetails);
			Optional<ProjectDetails> opPD = projectDetailsRepository.findByProjectName(projectDetails.getProjectName());
			log.debug("EmsServiceImpl:setProjectDetails setting project details to emploee");
			if(!opPD.isPresent()) {
				projectDetailsRepository.save(projectDetails);
			}
			employee.getProjectDetails().add(projectDetails);
			projectDetails.getEmployees().add(employee);
			employeeRepository.save(employee);
			log.info("EmsServiceImpl:setProjectDetails returns true");
			return true;
		}
		log.info("EmsServiceImpl:setProjectDetails employee doesn't exist,return false");
		return false;
	}
	@Override
	public Optional<EmployeeDto> getEmployee(Integer empId) {
		Optional<Employee> optionalEmp = employeeRepository.findByEmpId(empId);
		if(optionalEmp.isPresent()) {

			Employee employee = optionalEmp.get();
			EmployeeDto employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employeeDto);
		    return Optional.ofNullable(employeeDto);
			
			
		} return null;
	}


	

}
