package com.csmtech.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "PROJECT_MASTER")
public class ProjectMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INT_PROJECT_ID")
    private Integer projectId;

    @Column(name = "VCH_PROJECT_NAME")
    private String projectName;

    @Column(name = "INT_CREATED_BY")
    private Integer createdBy;

    @Column(name = "DTM_CREATED_ON")
    private Date createdOn;

    @Column(name = "INT_UPDATED_BY")
    private Integer updatedBy;

    @Column(name = "DTM_UPDATED_ON")
    private Date updatedOn;

    @Column(name = "BIT_DELETED_FLAG")
    private boolean deletedFlag;

  
}
