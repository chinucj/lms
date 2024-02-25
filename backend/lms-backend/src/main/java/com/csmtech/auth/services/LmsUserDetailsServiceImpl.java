package com.csmtech.auth.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.csmtech.auth.entities.UserMaster;
import com.csmtech.auth.repositories.UserMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LmsUserDetailsServiceImpl implements UserDetailsService {

	@Autowired

	private UserMasterRepository repo;

	private static final Logger log = LoggerFactory.getLogger(LmsUserDetailsServiceImpl.class);

	@Override

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("::  execution start of loadUserByUsername method");

		UserMaster entity = repo.findByEmail(username);
		
		log.info(entity.toString());

		log.info(":: execution end of loadUserByUsername method return to controller");

		return new org.springframework.security.core.userdetails.User(entity.getEmail(), entity.getPassword(),

				new ArrayList<>());

	}

}
