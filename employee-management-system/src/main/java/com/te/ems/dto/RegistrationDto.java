package com.te.ems.dto;

import java.time.LocalDate;
import java.util.List;

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
public class RegistrationDto {
	private String empName;
	private String empEmail;
	private LocalDate empDOB;
	private LocalDate empDOJ;
	private Gender gender;
	private SecondaryInfoDto secondaryInfo;
	private List<EducationalDetailsDto> educationalDetails;
	
}
