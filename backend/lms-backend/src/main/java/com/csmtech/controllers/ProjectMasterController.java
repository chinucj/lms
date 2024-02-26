package com.csmtech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csmtech.entities.ProjectMaster;
import com.csmtech.services.ProjectMasterService;

@RestController
@CrossOrigin("*")
public class ProjectMasterController {

	@Autowired
	private ProjectMasterService projectMasterService;

	@GetMapping("/projects")
	public ResponseEntity<List<ProjectMaster>> getProjectList() {
		List<ProjectMaster> projectList = projectMasterService.getProjectList();
		return ResponseEntity.ok().body(projectList);

	}

}
