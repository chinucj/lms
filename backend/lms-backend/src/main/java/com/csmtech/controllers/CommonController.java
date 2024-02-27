package com.csmtech.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class CommonController {

	
	@Value("${tempfile.path}")
	private String tempPath;

	@PostMapping("/setTempFile")
	public ResponseEntity<Map<String, Object>> setTempFile(@RequestParam("file") MultipartFile file , @RequestParam("fileType") String fileType)
			throws IOException {
		Map<String, Object> response = new HashMap<>();
		File f1 = null;
		SecureRandom rs = new SecureRandom();
		int randomNum = rs.nextInt(900 - 100) + 100;
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		String fileNameType = file.getOriginalFilename();
		if (fileNameType != null) {
			String[] fileArray = fileNameType.split("[.]");
			if (fileArray.length > 1) {
				String actualType = fileArray[fileArray.length - 1];
				System.out.println(fileType);
				if(fileType.equals("Video")) {
				f1 = new File(tempPath + "VIDEO" + "/" + randomNum + tt.getTime() + "." + actualType);
				}
				else if(fileType.equals("Document")) {
					f1 = new File(tempPath + "DOCUMENT" + "/" + randomNum + tt.getTime() + "." + actualType);
				}
				System.out.println(f1.getAbsolutePath());
				try (FileOutputStream fos = new FileOutputStream(f1);
						BufferedOutputStream bos = new BufferedOutputStream(fos)) {

					byte[] bytes = file.getBytes();
					bos.write(bytes);

					response.put("status", 200);
					response.put("fileName", f1.getName());

				} catch (Exception e) {
					response.put("status", 500);
					response.put("message", e.getMessage());
				}
			}
		}

		return ResponseEntity.ok().body(response);

	}
}
