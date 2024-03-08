package com.csmtech.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.repositories.SubModuleRepository;

@Service
public class SubModuleServiceImpl implements SubModuleService{

@Autowired
private SubModuleRepository subModuleRepository;
 
@Override
public List<Map<String,Object>> getSubmoduleList() {
return subModuleRepository.getSubmoduleList();
}

}
