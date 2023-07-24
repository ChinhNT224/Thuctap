package com.bridgelabz.hospital.dto;

import org.springframework.stereotype.Component;

@Component
public class CustomerDto {
	
	private String Name;
	private long Phonenumber;

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getPhonenumber() {
		return Phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		Phonenumber = phonenumber;
	}

}
