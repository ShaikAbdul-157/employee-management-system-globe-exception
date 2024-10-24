package com.te.ems.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateEmployeeDto {
	
	private Integer empId;
	private LocalDate empDOJ;
	private LocalDate empDOB;
	private String empName;
}
