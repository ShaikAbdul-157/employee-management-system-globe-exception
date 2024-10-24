package com.te.ems.dto;

import java.time.LocalDate;
import java.util.List;

import com.google.common.collect.Lists;
import com.te.ems.entity.EducationalDetails;
import com.te.ems.entity.ProjectDetails;
import com.te.ems.entity.SecondaryInfo;
import com.te.ems.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString 
@Builder
public class EmployeeDto {
	private Integer empId;
	private String empName;
	private String empEmail;
	private LocalDate empDOB;
	private LocalDate empDOJ;
	private Gender gender;
//	private SecondaryInfoDto secondaryInfoDto;
//	private List<EducationalDetails> educationalDetails;
//	private List<ProjectDetails> projectDetails ;
}
