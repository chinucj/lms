package com.csmtech.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class CommonController {

	@Value("${actualfile.path}")
	private String actualFilePath;
	
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
	
	@GetMapping(path="/viewFile/{fileName}")
	public ResponseEntity<?> downloadDocument(HttpServletResponse response,@PathVariable("fileName") String fileName) throws Exception{
	 
	String filePath="";
	String fileFormat="";
	String folderName="";
	filePath = actualFilePath;
	HttpHeaders headers = new HttpHeaders();
	headers.add("content-disposition","inline;filename=" + fileName);
	int lastDotIndex = fileName.lastIndexOf('.');
	if (lastDotIndex != -1) {
	    fileFormat = fileName.substring(lastDotIndex + 1);
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
	File file =new File(filePath +folderName+ "/" +fileName);
	InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
	String contentType = "";
	if (null != fileName && fileName.contains(".")) {
	String fileExtension = fileName.split("\\.")[1];
	if (fileExtension.equalsIgnoreCase("pdf"))
	contentType = "application/pdf";
	if (fileExtension.equalsIgnoreCase("xls"))
	contentType = "application/vnd.ms-excel";
	if (fileExtension.equalsIgnoreCase("xlsx"))
	contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	else if (fileExtension.equalsIgnoreCase("png"))
	contentType = "image/png";
	else if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg"))
	contentType = "image/jpeg";
	    else if (fileExtension.equalsIgnoreCase("ppt") || fileExtension.equalsIgnoreCase("pptx")) 
	        contentType = "application/vnd.ms-powerpoint";
	    else if (fileExtension.equalsIgnoreCase("mp4") || fileExtension.equalsIgnoreCase("avi")) 
	        contentType = "video/mp4"; // Adjust as needed based on the video format
	    
	}
	 
	 
	return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

}
