package com.csmtech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entities.DesignationMaster;


@Repository
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster, Integer> {

}

