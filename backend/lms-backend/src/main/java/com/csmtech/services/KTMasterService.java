package com.csmtech.services;

import java.util.List;
import java.util.Map;

import com.csmtech.dtos.KTMasterDto;
import com.csmtech.entities.KTMaster;

public interface KTMasterService {

	KTMaster saveKTMaster(KTMasterDto ktMasterDto);

	List<Map<String, Object>> getKTDetails();

	void deleteKT(Integer ktId);

	Map<String, Object> getKTById(Integer ktId);

}
