package com.csmtech.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.csmtech.auth.services.LmsUserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LmsUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthaniticationEntryPoint entry;

	@Autowired
	private JwtFilter jwtFilter;

//	@Override
//
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(userDetailsService);
//
//	}
//
//	@Bean
//
//	public AuthenticationEntryPoint authenticationEntryPoint() {
//
//		return new JwtAuthaniticationEntryPoint();
//
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//
//	}
	
	
	//my changes
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	//my changes

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/commonCaptchaGenerator/generate")

				.permitAll().antMatchers("/auth/generate-token").permitAll().antMatchers("/auth/authreq1").permitAll()
				.antMatchers("/viewFile/*").permitAll()
//				.antMatchers("/getFormName", "/getallOfficersApi", "/getallApprovalAction", "/fillWorkflow",
//
//						"/setWorkflow", "/trackstatus/showstatus", "/{imageName}",
//
//						"/scanning-data/viewScanDocument/{fileType}/{appDocId}", "/viewDocuments/{type}/{appDocId}",
//
//						"/land-bank/viewKhatianDigitalFile/{fileName}")
//
//				.permitAll().antMatchers("/landAppregistratation/registratationtookan").permitAll()
//
//				.antMatchers("/api/sendotp", "/api/setnewpassword", "/api/matchotp").permitAll()
//
//				.antMatchers("/userregister/saveuser", "/userregister/savemobileno", "/userregister/updatemobilenos",
//
//						"/userregister/varifiedotp")
//
//				.permitAll().antMatchers("/api/officer/register").permitAll()
//
//				.antMatchers("/mainpagination/registerpage").permitAll()
//
//				.antMatchers("/showcitizenInfo/getcitizeninfo", "/faq/questionsandanswers").permitAll()
//
//				.antMatchers("/mainpermission/getrole", "/mainpermission/getmodulewisemenu",
//
//						"/mainpermission/saveBatchrecord", "/mainpermission/updateBatchrecord")
//
//				.permitAll().antMatchers("/saveFileToTemp", "/fillDropDowns").permitAll()
//
//				.antMatchers("/showcitizenInfo/pagination").permitAll()
//
//				.antMatchers("/fillDropDown", "/nocUserrecord/savenocRecxord", "/nocUserrecord/savenocNocRecxord",
//
//						"/nocUserrecord/saveNocFileREcord")
//
//				.permitAll().antMatchers("/showcitizenInfo/search", "/raisequery", "/viewquery").permitAll()
//
//				.antMatchers("/api/getSummaryDetails").permitAll()
//
//				.antMatchers("/mainView/getlandAppDetails", "/api/notifications/getnotifications",
//
//						"/api/notifications/viewnotifications", "/api/notifications/getonenotification",
//
//						"/api/notifications/add", "/api/notifications/update", "/api/notifications/delete",
//
//						"/api/tender/gettender")
//
//				.permitAll().antMatchers("/api/banner/districtcodes").permitAll()
//
//				.antMatchers("/ckEditorFileUpload", "/ckEditorImageUpload", "/downloadDocument/**", "/viewDocument/**",
//
//						"/downloadDocumentPost")
//
//				.permitAll()
//
//				.antMatchers("/mainRecord/distDropDownData", "/mainRecord/getThasilRecord",
//
//						"/mainRecord/getVillageRecord", "/mainRecord/getVillagekathaRecordMore",
//
//						"/mainRecord/tahasilLogin", "/mainRecord/plotVerification")
//
//				.permitAll()
//
//				.antMatchers("/grievance-module/grievance/addEdit", "/grievance-module/grievance/download/{name}",
//
//						"/meeting-management/meeting/download/{name}",
//
//						"/manage-notification/notification/download/{name}",
//
//						"/landOfficerVerification/plotvaluation/download/{name}")
//
//				.permitAll().antMatchers("/mobileLogin/getToken").permitAll().antMatchers("/mobileLogin/login")
//
//				.permitAll().antMatchers("/mobileLogin/otpVerification").permitAll()
//
//				.antMatchers("/mobileGrievance/CoInspection").permitAll()
//
//				.antMatchers("/mobileTahasilLogin/tahasilLogin").permitAll()
//
//				.antMatchers("/mobileTahasilLogin/tahasilMobileInsertion").permitAll()
//
//				.antMatchers("/mobileTahasilLogin/tahasilOtpVerification").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/getVillageDetails").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/getVillageDetailsByPlot").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/savePlotAction").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/fetchLandTypeDetails").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/fetchLandUseDetails").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/fetchLandUseVerificationCompletedDetails").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/fetchWebLandUseVerificationCompletedDetails").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/tahasilPlotList").permitAll()
//
//				.antMatchers("/mobileTahasilDetails/viewImage").permitAll()
//
//				// savePlotAction fetchCompleteGrievance getGrievanceByNum fetchDetails
//
//				// tahasilPlotList
//
//				// fetchLandTypeDetails tahasilSubmit /viewImage
//
//				.antMatchers("/landOfficerVerification/assignToCO").permitAll()
//
//				.antMatchers("/landOfficerVerification/coInspectionSubmit").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchDetails").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchCount").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchPendingRecords").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchPendingRecordsByVillage").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchCompleteRecords").permitAll()
//
//				.antMatchers("/landOfficerVerification/viewCoInspectionDetails").permitAll()
//
//				.antMatchers("/landOfficerVerification/getVillageInformation").permitAll()
//
//				.antMatchers("/landOfficerVerification/tahasildarSubmit").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchCountForTahasil").permitAll()
//
//				.antMatchers("/landOfficerVerification/getVillageInformationForTahasil").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchTahasildarCompleteRecords").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchTahasildarPendingRecords").permitAll()
//
//				.antMatchers("/landOfficerVerification/fetchTahasilPendingRecordsByVillage").permitAll()
//
//				.antMatchers("/landOfficerVerification/viewTahasildarInspectionDetails").permitAll()
//
//				.antMatchers("/landOfficerVerification/viewWebTahasildarInspectionDetails").permitAll()
//
//				.antMatchers("/mobileGrievance/saveGrievance").permitAll()
//
//				.antMatchers("/mobileGrievance/fetchPendingGrievance").permitAll()
//
//				.antMatchers("/mobileGrievance/fetchCompleteGrievance").permitAll()
//
//				.antMatchers("/mobileGrievance/fetchCountGrievance").permitAll()
//
//				.antMatchers("/mobileGrievance/getGrievanceByNum").permitAll()
//
//				.antMatchers("/mobileGrievance/saveMobile").permitAll().antMatchers("/mobileGrievance/areaForPlot")
//
//				.permitAll().antMatchers("/mobileGrievance/otpVerification").permitAll()
//
//				.antMatchers("/mobileGrievance/grievance/download/{name}").permitAll()
//
//				.antMatchers("/land-bank/plotinformation/plotInfoForTableView").permitAll()
//
//				.antMatchers("/land-bank/plotinformation/viewall").permitAll().antMatchers("/api/users/paymentPending")
//
//				.permitAll().antMatchers("/auction/generatePdfTendreAuction").permitAll()
//
//				.antMatchers("/landPostAllotment/fetchPrePostAllotmentList").permitAll()
//
//				.antMatchers("/landPostAllotment/fetchPostAllotmentList").permitAll()
//
//				.antMatchers("/landPostAllotment/fetchPostAllotmentPendingPlotDetails").permitAll()//
//
//				.antMatchers("/landPostAllotment/fetchPostAllotmentListDetails").permitAll()
//
//				.antMatchers("/landPostAllotment/fetchPostAllotmentCompletedList").permitAll()
//
//				.antMatchers("/landPostAllotment/fetchPostAllotmentCompletedPlotDetails").permitAll()
//
//				.antMatchers("/landPostAllotment/CoInspectionSubmit").permitAll()
//
//				.antMatchers("/landPostAllotment/assignToCOForLandPostAllotment").permitAll()
//
//				.antMatchers("/img/gs-guide-websocket").permitAll()
//
//				.antMatchers("/auction/bidderregistraration/getddhar").permitAll()
//
//				.antMatchers("/reports/land-information/generateDistrictExcel",
//
//						"/reports/land-information/generateTahasilWithDistrictIdReport/{districtCode}",
//
//						"/reports/land-information/generateVillageWithTahasilIdReport/{tahasilCode}",
//
//						"/reports/land-information/generateKhataWithVillageIdReport/{villageCode}",
//
//						"/reports/land-information/generatePlotWithKhataIdReport/{khataCode}")
//
//				.permitAll()
//
//				.antMatchers("/reports/landapplication/getLandAppDetailsExcel",
//
//						"/reports/landapplication/getDistrictWiseLandExcel/{districtCode}",
//
//						"/reports/landapplication/getTahasilWiseLandExcel/{tahasilCode}")
//
//				.permitAll()
				.anyRequest().authenticated().and()

				.exceptionHandling(ex -> ex.authenticationEntryPoint(entry))

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
