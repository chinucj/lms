package com.csmtech.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class UserRegistrationDto {

	private Integer userId;

	private String password;

	private String fullName;

	private Integer designationId;

	private String contactNumber;

	private String emailId;

	private String normalPassword;

	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;

	private Boolean deletedFlag;

	private Integer userType = 2;

	private Integer projectId;

	private String location;
}
