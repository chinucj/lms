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
	public ResponseEntity<KTMaster> saveKTMaster(@RequestBody KTMasterDto ktMasterDto) throws Exception {
		KTMaster ktMaster = ktMasterService.saveKTMaster(ktMasterDto);

		// for re;[move the file temppath to actualpath and delete the file from
		// temppath

		List<String> fileUploadList = new ArrayList<>();
		String fileFormat = null;
		String folderName = null;
		fileUploadList.add(ktMasterDto.getKtFilePath());
		for (String fileUpload : fileUploadList) {
			if (fileUpload != null && (!fileUpload.equals(""))) {
				int lastDotIndex = fileUpload.lastIndexOf('.');
				if (lastDotIndex != -1) {
				    fileFormat = fileUpload.substring(lastDotIndex + 1);
				    System.out.println(fileFormat);
				} else {
				    throw new Exception("No file format found");
				}
				if(fileFormat.equals("mp4") || fileFormat.equals("avi") || 
						fileFormat.equals("wmv") || fileFormat.equals("mov") || 
						fileFormat.equals("flv") || fileFormat.equals("mpeg") || 
						fileFormat.equals("mkv") || fileFormat.equals("webm") || fileFormat.equals("3gp")) {
					folderName = "VIDEO";
				}
				else {
					folderName = "DOCUMENT";
				}
				File file = new File(tempFilePath + folderName + "/" + fileUpload);
				if (file.exists()) {
					File srcFile = new File(tempFilePath + folderName + "/" +  fileUpload);
					File destFile = new File(actualFilePath + folderName + "/" + fileUpload);
					try {
						Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						Files.delete(srcFile.toPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return ResponseEntity.ok().body(ktMaster);

	}

	@GetMapping("/kt")
	public ResponseEntity<List<Map<String, Object>>> getKTDetails() {
		List<Map<String, Object>> ktDetails = ktMasterService.getKTDetails();
		return ResponseEntity.ok().body(ktDetails);

	}

	@DeleteMapping("/kt/{ktId}")
	public ResponseEntity<Map<String, Object>> deleteKT(@PathVariable("ktId") Integer ktId) {

		ktMasterService.deleteKT(ktId);
		Map<String, Object> response = new HashMap<>();
		response.put("status", 200);
		response.put("deleted", "Employee is deleted successfuly");
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/kt/{ktId}")
	public ResponseEntity<Map<String, Object>> getKTById(@PathVariable("ktId") Integer ktId) {
		Map<String, Object> ktDetails = ktMasterService.getKTById(ktId);
		return ResponseEntity.ok().body(ktDetails);

	}

}
