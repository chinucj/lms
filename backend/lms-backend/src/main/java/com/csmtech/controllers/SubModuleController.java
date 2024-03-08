package com.csmtech.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csmtech.services.SubModuleService;

@RestController
@CrossOrigin("*")
public class SubModuleController {

	@Autowired
	private SubModuleService subModuleService;

	@GetMapping("/sub-module-kt")
	public ResponseEntity<List<Map<String, Object>>> getSubModuleList() {
		List<Map<String, Object>> subModuleList = subModuleService.getSubmoduleList();
		return ResponseEntity.ok().body(subModuleList);

	}

}
