package com.csmtech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entities.ProjectMaster;
import com.csmtech.repositories.ProjectMasterRepository;


@Service
public class ProjectMasterServiceImpl implements ProjectMasterService {

 
@Autowired
private ProjectMasterRepository projectMasterRepository;
@Override
public List<ProjectMaster> getProjectList() {
return projectMasterRepository.findAll();
}

}