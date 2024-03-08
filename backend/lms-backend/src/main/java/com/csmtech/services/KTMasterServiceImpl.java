package com.csmtech.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.dtos.KTMasterDto;
import com.csmtech.entities.KTMaster;
import com.csmtech.repositories.KTMasterRepository;

@Service
public class KTMasterServiceImpl implements KTMasterService {

	@Autowired
	private KTMasterRepository ktMasterRepository;

	@Override
	public KTMaster saveKTMaster(KTMasterDto ktMasterDto) {
		KTMaster ktMaster = new KTMaster();

		if (ktMasterDto.getKtId() != 0) {
			ktMaster.setKtId(ktMasterDto.getKtId());
			ktMaster.setCreatedBy(1);
			ktMaster.setCreatedOn(ktMasterDto.getCreatedOn());
			ktMaster.setUpdatedBy(1);
			ktMaster.setUpdatedOn(new Date());
		}
		else {
			ktMaster.setCreatedBy(1);
			ktMaster.setCreatedOn(new Date());
		}
		ktMaster.setKtName(ktMasterDto.getKtName());
		ktMaster.setSubModuleId(ktMasterDto.getSubModuleId());
		ktMaster.setKtFormat(ktMasterDto.getKtFormat());
		ktMaster.setKtPath(ktMasterDto.getKtFilePath());
		
		if(ktMasterDto.getSubModuleId() == 4) {
			ktMaster.setProjectId(ktMasterDto.getProjectId());
		}
		else if(ktMasterDto.getSubModuleId() == 5) {
			ktMaster.setProjectId(ktMasterDto.getProjectId());
			ktMaster.setDesignationId(ktMasterDto.getDesignationId());
		}
		
		ktMaster.setDeletedFlag(false);

		return ktMasterRepository.save(ktMaster);

	}

	@Override
	public List<Map<String, Object>> getKTDetails() {
		return ktMasterRepository.getgetKTDetails();
	}

	@Override
	public void deleteKT(Integer ktId) {
		ktMasterRepository.deleteKT(ktId);
	}

	@Override
	public Map<String, Object> getKTById(Integer ktId) {
		return ktMasterRepository.getKTById(ktId);
	}

}
