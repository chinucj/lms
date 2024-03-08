package com.csmtech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entities.DesignationMaster;
import com.csmtech.repositories.DesignationMasterRepository;

@Service
public class DesignationMasterServiceImpl implements DesignationMasterService {

	@Autowired
	private DesignationMasterRepository designationMasterRepository;
	 
	@Override
	public List<DesignationMaster> getDesignationList() {
	return designationMasterRepository.findAll();
	}

}
