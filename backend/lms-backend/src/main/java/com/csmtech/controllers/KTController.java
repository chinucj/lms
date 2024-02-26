package com.csmtech.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.csmtech.dtos.KTMasterDto;
import com.csmtech.entities.KTMaster;
import com.csmtech.services.KTMasterService;

@RestController
@CrossOrigin("*")
public class KTController {
	
	@Value("${tempfile.path}")
	private String tempFilePath;
	 
	@Value("${actualfile.path}")
	private String actualFilePath;
	
	@Autowired
	private KTMasterService ktMasterService;
	 

	@PostMapping("/kt")
	public ResponseEntity<KTMaster> saveKTMaster(@RequestBody KTMasterDto ktMasterDto ){
	KTMaster ktMaster =ktMasterService.saveKTMaster(ktMasterDto);
	 
	//for re;[move the file temppath to actualpath and delete the file from temppath
	 
	List<String> fileUploadList=new ArrayList<>();
	fileUploadList.add(ktMasterDto.getKtFilePath());
	for(String fileUpload:fileUploadList) {
	if(fileUpload !=null && (!fileUpload.equals(""))) {
	File file=new File(tempFilePath + fileUpload);
	if(file.exists()) {
	File srcFile=new File(tempFilePath + fileUpload);
	File destFile=new File(actualFilePath + fileUpload);
	try {
	Files.copy(srcFile.toPath(), destFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
	Files.delete(srcFile.toPath());
	}catch(IOException e) {
	e.printStackTrace();
	}
	}
	}
	}
	return ResponseEntity.ok().body(ktMaster);
	 
	}
	 
	@GetMapping("/kt")
	public ResponseEntity<List<Map<String,Object>>> getKTDetails(){
	List<Map<String,Object>> ktDetails=ktMasterService.getKTDetails();
	return ResponseEntity.ok().body(ktDetails);
	 
	 
	}
	 
	
	 
	@DeleteMapping("/deleteKT")
	public ResponseEntity<Map<String,Object>> deleteKT(@RequestParam("ktId") Integer ktId){
	 
	    ktMasterService.deleteKT(ktId);
	Map<String,Object> response=new HashMap<>();
	response.put("status", 200);
	response.put("deleted", "Employee is deleted successfuly");
	return ResponseEntity.ok().body(response);
	}
	 
	@GetMapping("/ktById/{ktId}")
	public ResponseEntity<Map<String,Object>> getKTById(@PathVariable Integer ktId){
	Map<String,Object> ktDetails=ktMasterService.getKTById(ktId);
	return ResponseEntity.ok().body(ktDetails);
	 
	}
	 
	 
	
}
