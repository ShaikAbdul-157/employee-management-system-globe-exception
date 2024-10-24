package com.te.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecondaryInfoDto {
	private String aadhaarNum;
	private String panNum;
	private String fatherName;
	private String motherName;
}
