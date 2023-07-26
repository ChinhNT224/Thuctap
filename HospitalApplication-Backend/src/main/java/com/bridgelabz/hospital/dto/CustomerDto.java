package com.bridgelabz.hospital.dto;

public class CustomerDto {
	private String name;
	private String email;
	private String password;
	private Long mobileNumber;
	private Long health_insurance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getHealth_insurance() {
		return health_insurance;
	}

	public void setHealth_insurance(Long health_insurance) {
		this.health_insurance = health_insurance;
	}
}
