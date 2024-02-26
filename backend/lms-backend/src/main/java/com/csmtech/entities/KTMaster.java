package com.csmtech.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "KT_MASTER")
public class KTMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INT_KT_ID")
    private Integer ktId;

    @Column(name = "VCH_KT_NAME")
    private String ktName;

    @Column(name = "VCH_KT_TYPE")
    private String ktType;

    @Column(name = "VCH_KT_FORMAT")
    private String ktFormat;

    @Column(name = "VCH_KT_PATH")
    private String ktFilePath;

    @Column(name = "INT_PROJECT_ID")
    private Integer projectId;

    @Column(name = "INT_CREATED_BY")
    private int createdBy;

    @Column(name = "DTM_CREATED_ON")
    private Date createdOn;

    @Column(name = "INT_UPDATED_BY")
    private Integer updatedBy;

    @Column(name = "DTM_UPDATED_ON")
    private Date updatedOn;

    @Column(name = "BIT_DELETED_FLAG")
    private boolean deletedFlag;

 
}


