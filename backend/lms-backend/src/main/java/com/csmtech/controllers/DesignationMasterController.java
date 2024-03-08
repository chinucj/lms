package com.csmtech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csmtech.entities.DesignationMaster;
import com.csmtech.services.DesignationMasterService;

@RestController
@CrossOrigin("*")
public class DesignationMasterController {
 
@Autowired
private DesignationMasterService designationMasterService;
 
@GetMapping("/designation")
public ResponseEntity<List<DesignationMaster>> getDesignationList(){
List<DesignationMaster> designationList =designationMasterService.getDesignationList();
return ResponseEntity.ok().body(designationList);
 
}

}

