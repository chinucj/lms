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

		if (ktMasterDto.getKtId() != null) {
			ktMaster.setKtId(ktMasterDto.getKtId());
		}
		ktMaster.setKtName(ktMasterDto.getKtName());
		ktMaster.setKtType(ktMasterDto.getKtType());
		ktMaster.setKtFormat(ktMasterDto.getKtFormat());
		ktMaster.setKtFilePath(ktMasterDto.getKtFilePath());
		ktMaster.setProjectId(ktMasterDto.getProjectId());
		ktMaster.setCreatedBy(1);
		ktMaster.setCreatedOn(new Date());
		ktMaster.setUpdatedBy(1);
		ktMaster.setUpdatedOn(new Date());
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
