package com.csmtech.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SUB_MODULE")
public class SubModule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_MODULE_ID")
    private Integer subModuleId;
    
    @Column(name = "SUB_MODULE_NAME")
    private String subModuleName;
    
    @Column(name = "MODULE_ID")
    private Integer moduleId;
    
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    
    @Column(name = "CREATED_ON")
    private Date createdOn;
    
    @Column(name = "UPDATED_BY")
    private Integer updatedBy;
    
    @Column(name = "UPDATED_ON")
    private Date updatedOn;
    
    @Column(name = "DELETED_FLAG")
    private Boolean deletedFlag;
    
  
}
