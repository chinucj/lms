package com.csmtech.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "DESIGNATION_MASTER")
public class DesignationMaster {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESGN_ID")
    private Long desgnId;

    @Column(name = "DESGN_NAME")
    private String desgnName;

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
