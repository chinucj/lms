package com.csmtech.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class KTMasterDto {

	private Integer ktId;

	private String ktName;

	private String ktFormat;

	private String ktFilePath;

	private Integer projectId;
	
	private Integer designationId;
	
	private Integer subModuleId;
	
	private Date createdOn;

}
