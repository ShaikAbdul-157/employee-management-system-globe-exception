package com.te.ems.enums;


public enum Gender {
	MALE("MALE"),FEMALE("FEMALE"),OTHERS("OTHERS");
	
	private final String genderType;
	
	Gender(String genderType){
		this.genderType = genderType;
	}
	
	public String getGenderType() {
		return genderType;
	}
}
