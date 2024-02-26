package com.csmtech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.csmtech.auth.entities.UserMaster;
import com.csmtech.auth.repositories.UserMasterRepository;

@SpringBootApplication
public class LmsBackendApplication implements CommandLineRunner{

	@Autowired
	private UserMasterRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(LmsBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserMaster uu = repo.findById(1).get();
		uu.setPassword(new BCryptPasswordEncoder().encode("admin"));
		repo.save(uu);
	}

}
