package com.bridgelabz.hospital.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Users  {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long userId;
		private String name;
		private String email;
		private String password;
		private Long mobileNumber;
		private LocalDateTime createdDate;
		private boolean isVerified;
		private String role;
		private Integer active;
		private  Integer isdelete;

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

		@Override
		public String toString() {
			return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", mobileNumber=" + mobileNumber + ", createdDate=" + createdDate + ", isVerified=" + isVerified
					+ ", role=" + role + "]";
		}

	public Users(long userId, String name, String email, String password, Long mobileNumber, LocalDateTime createdDate, boolean isVerified, String role, Integer active) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.createdDate = createdDate;
		this.isVerified = isVerified;
		this.role = role;
		this.active = active;

	}

	public Users(long userId, String name, String email, String password, Long mobileNumber, LocalDateTime createdDate, boolean isVerified, String role, Integer active, Integer isdelete) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.createdDate = createdDate;
		this.isVerified = isVerified;
		this.role = role;
		this.active = active;
		this.isdelete = isdelete;
	}

	public Users() {
	}

	public Users(long userId, String name, String email, String password, Long mobileNumber, LocalDateTime createdDate, boolean isVerified, String role) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.createdDate = createdDate;
		this.isVerified = isVerified;
		this.role = role;
	}

	public long getUserId() {
			return userId;
		}


		public void setUserId(long userId) {
			this.userId = userId;
		}


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

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}


		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}


		public boolean isVerified() {
			return isVerified;
		}


		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}


		public String getRole() {
			return role;
		}

	public void setRole(String role) {
		this.role = role;
	}

}

