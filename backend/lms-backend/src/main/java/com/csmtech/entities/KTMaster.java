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
    @Column(name = "KT_ID")
    private Integer ktId;

    @Column(name = "KT_NAME")
    private String ktName;

    @Column(name = "KT_TYPE")
    private String ktType;

    @Column(name = "KT_FORMAT")
    private String ktFormat;

    @Column(name = "KT_PATH")
    private String ktPath;

    @Column(name = "PROJECT_ID")
    private Integer projectId;

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

    @Column(name = "KT_MOD_SUB_DTLS")
    private String ktModSubDtls;

    @Column(name = "MODULE_ID")
    private Integer moduleId;

    @Column(name = "SUB_MODULE_ID")
    private Integer subModuleId;

    @Column(name = "SUP_SUB_MODULE_ID")
    private Integer supSubModuleId;

    @Column(name = "SUP_SUB_DTLS_ID")
    private Integer supSubDtlsId;

    @Column(name = "MATERIAL_ID")
    private Integer materialId;

    @Column(name = "SESSION_NO")
    private Integer sessionNo;

    @Column(name = "DESGN_ID")
    private Integer designationId;

 
}


