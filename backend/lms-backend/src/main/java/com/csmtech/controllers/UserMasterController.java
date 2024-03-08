package com.csmtech.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csmtech.auth.entities.UserMaster;
import com.csmtech.dtos.UserRegistrationDto;
import com.csmtech.services.UserMasterService;

@RestController
@CrossOrigin("*")
public class UserMasterController {

	@Autowired
	private UserMasterService userMasterService;

	@PostMapping("/userMaster")
	public ResponseEntity<UserMaster> saveUserMaster(@RequestBody UserRegistrationDto userMasterDto) {
		UserMaster userMaster = userMasterService.saveUserMaster(userMasterDto);
		return ResponseEntity.ok().body(userMaster);

	}

	@GetMapping("/userMaster")
	public ResponseEntity<List<Map<String, Object>>> getUseMasterList() {
		List<Map<String, Object>> UserMasterList = userMasterService.getUseMasterList();
		return ResponseEntity.ok().body(UserMasterList);
	}

	@DeleteMapping("/userMaster/{userId}")
	public ResponseEntity<Map<String, Object>> deleteUserMaster(@PathVariable("userId") Integer userId) {
		userMasterService.deleteUserMaster(userId);
		Map<String, Object> response = new HashMap<>();
		response.put("status", 200);
		response.put("deleted", "UserMaster is deleted successfuly");
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/userMaster/{userId}")
	public ResponseEntity<Map<String, Object>> getUserMasterById(@PathVariable("userId") Integer userId) {
		Map<String, Object> userMaster = userMasterService.getUserMasterById(userId);
		return ResponseEntity.ok().body(userMaster);

	}
}
