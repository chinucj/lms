package com.csmtech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entities.ProjectMaster;
@Repository
public interface ProjectMasterRepository extends JpaRepository<ProjectMaster, Integer> {

}
