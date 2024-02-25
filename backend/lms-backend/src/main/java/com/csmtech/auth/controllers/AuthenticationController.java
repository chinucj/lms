package com.csmtech.auth.controllers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csmtech.auth.dtos.LoginRequestDto;
import com.csmtech.auth.entities.UserMaster;
import com.csmtech.auth.repositories.UserMasterRepository;
import com.csmtech.auth.services.AuthenticationService;
import com.csmtech.auth.services.LmsUserDetailsServiceImpl;
import com.csmtech.auth.utils.CommonCaptchaGenerate;
import com.csmtech.auth.utils.CommonConstant;
import com.csmtech.auth.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private LmsUserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticationService service;

	@Autowired
	private UserMasterRepository loginrepo;

	@Value("${lms.bcryptpassword.secretKey}")
	private String secretKey;

	// Test To Create The welcome Method

	@SuppressWarnings("rawtypes")
	@GetMapping("/authreq1")
	public ResponseEntity welcome() {

		// Retrieve the authenticated user-name

		@SuppressWarnings("unused")

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		JSONObject response = new JSONObject();

		response.put(CommonConstant.STATUS_KEY, 200);

		response.put(CommonConstant.RESULT, "Welcome");

		return ResponseEntity.ok(response.toString());

	}

	// method will work generate Token and login

	@SuppressWarnings({ "unused", "rawtypes" })
	@PostMapping("/generate-token")
	public ResponseEntity generateToken(@RequestBody LoginRequestDto authRequest) throws Exception {

		Boolean validateCaptcha = captchaValidation(authRequest);

		if (!validateCaptcha) {

			JSONObject response = new JSONObject();

			response.put(CommonConstant.STATUS_KEY, 223);

			response.put(CommonConstant.RESULT, "Invalid captcha");

			return ResponseEntity.ok(response.toString());

		}
//
//		log.info(" ::Captcha Validate Success..!!");
//
//		log.info(" ::generateToken method are start");
//
//		String matchPs = null;
//
		UserMaster getalldata = null;
//
//		Integer count = 0;
//
//		Integer countCitizen = 0;
//
//		List<Long> rollid = null;
//
//		log.info("query starts");
//
//		log.info("inside officer or admin");
//
		getalldata = service.findByEmail(authRequest.getEmail());
//
//		log.info("getalldata: " + getalldata);
//
//		log.info("query ends with officer or admin data: ");
//
//		// count = service.findByusernameBlockOrNot(authRequest.getEmail());
//
//		if (getalldata == null) {
//
//			JSONObject response = new JSONObject();
//
//			response.put(CommonConstant.STATUS_KEY, 432);
//
//			response.put(CommonConstant.RESULT, CommonConstant.INVALID_USERNAME_PASSWORD);
//
//			return ResponseEntity.ok(response.toString());
//
//		}
//
//		String password = authRequest.getPassword();
//
//		// check user is active or not
//
////		if (count == 0 && countCitizen == 0) {
////
////			JSONObject response = new JSONObject();
////
////			response.put(CommonConstant.STATUS_KEY, 444);
////
////			response.put(CommonConstant.RESULT, "User blocked, Contact Admin");
////
////			return ResponseEntity.ok(response.toString());
////
////		}
//
//		// Retrieve the role form the user
//
////		if (getalldata != null) {
////
////			log.info("inside retrieving role Id ");
////
////			rollid = loginrepo.findRoleIdsByUserId(getalldata.getUsername());
////
////			log.info("query starts for roleId : ");
////
////		}
//
//		String providedPassword = authRequest.getPassword();
//		log.info(providedPassword);
//		String storedPassword = getalldata.getPassword();
//		log.info(storedPassword);
//
//		if ((providedPassword == null && storedPassword == null)
//
//				&& (storedPassword != null && !storedPassword.equals(providedPassword))) {
//
//			JSONObject response = new JSONObject();
//
//			response.put(CommonConstant.STATUS_KEY, 500);
//
//			response.put(CommonConstant.RESULT, CommonConstant.INVALID_USERNAME_PASSWORD);
//
//			return ResponseEntity.ok(response.toString());
//
//		}
//
//		String hashedPassword = getalldata.getPassword();
//
//		log.info(" ::BCryptPasswordEncoder start the matching ");
//
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		String generatedBcryptHexeash = encoder.encode(secretKey + password);
//
//		boolean isMatch = encoder.matches(secretKey + password, hashedPassword);
//
//		if (isMatch) {
//
//			matchPs = getalldata.getPassword();
//
//		} else {
//
//			matchPs = "Authentication failed. Please try again.";
//
//		}
//
//		log.info(" :: isMatch is validate sucesfully");

		try {

			log.info("inside try method of authentication");

			authenticationManager

					.authenticate(
							new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
			log.info("afteer auth");

		} catch (Exception ex) {

			log.info("exception in authenticating user !!" + ex.getMessage());
			ex.printStackTrace();

			JSONObject response = new JSONObject();

			response.put(CommonConstant.STATUS_KEY, 500);

			response.put(CommonConstant.RESULT, CommonConstant.INVALID_USERNAME_PASSWORD);

			return ResponseEntity.ok(response.toString());

		}

		log.info(" :: Execution end tookan return");

		JSONObject response = new JSONObject();

		response.put(CommonConstant.STATUS_KEY, 200);

		response.put("fullName", getalldata.getFullName());
//
		response.put("emailId", getalldata.getEmail());
//
//		response.put("userName", getalldata.getUsername());
//
		response.put("mobileNo", getalldata.getContactNumber());
//
//		response.put("userId", getalldata.getId());
//
		response.put("userType", getalldata.getUserType());
//
//		response.put("isFirstLogin", getalldata.getIsFirstLogin());
//
//		response.put("districtCode", getalldata.getDistrictCode());
//
//		response.put("tahasilCode", getalldata.getTahasilCode());
//
//		response.put("userRollId", rollid.get(0));

		response.put(CommonConstant.RESULT,
				jwtUtil.generateToken(userDetailsService.loadUserByUsername(authRequest.getEmail())));

		return ResponseEntity.ok(response.toString());

	}

	// Validate C

	private boolean captchaValidation(LoginRequestDto authRequest) {

		boolean validateCaptcha;

		Integer captchaResult = CommonCaptchaGenerate.get(authRequest.getCaptchaId());

		if (captchaResult != null && authRequest.getAnswer().equals(captchaResult)) {

			CommonCaptchaGenerate.remove(authRequest.getCaptchaId());

			validateCaptcha = true;

		} else {

			validateCaptcha = false;

		}

		return validateCaptcha;

	}

}
