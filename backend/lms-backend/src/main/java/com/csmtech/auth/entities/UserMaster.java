package com.csmtech.auth.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_master")
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INT_USER_ID")
	private Integer userId;

	@Column(name = "VCH_PASSWORD")
	private String password;

	@Column(name = "VCH_FULL_NAME")
	private String fullName;

	@Column(name = "INT_DESGN_ID")
	private Integer designationId;

	@Column(name = "VCH_CONTACT_NO")
	private String contactNumber;

	@Column(name = "VCH_EMAIL_ID")
	private String email;

	@Column(name = "VCH_NORMAL_PASS")
	private String normalPassword;

	@Column(name = "INT_CREATED_BY")
	private Integer createdBy;

	@Column(name = "DTM_CREATED_ON")

	private Date createdOn;

	@Column(name = "INT_UPDATED_BY")
	private Integer updatedBy;

	@Column(name = "DTM_UPDATED_ON")
	private Date updatedOn;

	@Column(name = "BIT_DELETED_FLAG")
	private Boolean deletedFlag;

	@Column(name = "INT_USER_TYPE")
	private Integer userType;

	@Column(name = "INT_PROJECT_ID")
	private Integer projectId;

	@Column(name = "VCH_LOCATION")
	private String location;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNormalPassword() {
		return normalPassword;
	}

	public void setNormalPassword(String normalPassword) {
		this.normalPassword = normalPassword;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", password=" + password + ", fullName=" + fullName + ", designationId="
				+ designationId + ", contactNumber=" + contactNumber + ", email=" + email + ", normalPassword="
				+ normalPassword + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy
				+ ", updatedOn=" + updatedOn + ", deletedFlag=" + deletedFlag + ", userType=" + userType
				+ ", projectId=" + projectId + ", location=" + location + "]";
	}

}