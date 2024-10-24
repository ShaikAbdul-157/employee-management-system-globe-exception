package com.te.ems.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.te.ems.dto.EmployeeDto;
import com.te.ems.dto.ProjectDetailsDto;
import com.te.ems.dto.RegistrationDto;
import com.te.ems.entity.Employee;
import com.te.ems.exception.EmployeeDataNotFoundException;
import com.te.ems.exception.ProjectDetailsNotUpdatedException;
import com.te.ems.exception.RegistrationNotSuccessfullException;
import com.te.ems.response.GeneralResponse;
import com.te.ems.service.EmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class EmsController {

	private final EmsService emsService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(path = "/employee/registration")
	public GeneralResponse<Integer> registration(@RequestBody RegistrationDto registrationDto) {
		log.info("EmsController:registration executed started,trying to register user with data {}", registrationDto);
		Optional<Integer> empId = emsService.registration(registrationDto);

		if (empId.isPresent()) {
			log.debug("EmsController:registration returning the response");
			return new GeneralResponse<Integer>("registration Successfull", empId.get());
		}
		log.debug("EmsController:registration exception occured");
		log.debug("EmsController:registration throwing new RegistrationNotSuccessfullException");
		throw new RegistrationNotSuccessfullException("Registration failed");
	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping(path = "/employee/project/{empEmail}")
	public GeneralResponse<Boolean> setProjectDetails(@PathVariable String empEmail,
			@RequestBody ProjectDetailsDto projectDetailsDto) {
		log.info("EmsController:setProjectDetails started,employee email {},project details {}", empEmail,
				projectDetailsDto);
		Boolean isUpdated = emsService.setProjectDetails(empEmail, projectDetailsDto);
		if (isUpdated) {
			return new GeneralResponse<Boolean>("project details Updated", isUpdated);
		}
		log.debug("EmsController:setProjectDetails throwing ProjectDetailsNotUpdatedException");
		throw new ProjectDetailsNotUpdatedException("project details could not be updated");
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "/employee/{empId}")
	public GeneralResponse<EmployeeDto> getEmployee(@PathVariable Integer empId) {
		Optional<EmployeeDto> getEmployeeDto = emsService.getEmployee(empId);
		if(getEmployeeDto.isPresent()) {
			return new GeneralResponse<EmployeeDto>("employee data is present in the database", getEmployeeDto.get());
		} 
		throw new EmployeeDataNotFoundException("employee data is not present in the database");
		
	}
}
