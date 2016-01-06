package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.RepairProgressDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.RepairProgress;

public class RepairProgressService {
	
	private RepairProgressDao repairProgressDao;

	public RepairProgressDao getRepairProgressDao() {
		return repairProgressDao;
	}

	public void setRepairProgressDao(RepairProgressDao repairProgressDao) {
		this.repairProgressDao = repairProgressDao;
	}
	
	public void insertProgress(RepairProgress repairProgress){
		repairProgressDao.insertProgress(repairProgress);
	}

	public List<RepairProgress> getProgressesByRepair_id(int repair_id){
		List<RepairProgress> repair_Progresses=repairProgressDao.getProgressesByRepair_id(repair_id);
		return repair_Progresses;
	}
	
	public List<RepairProgress> getProgressesByPage(int repair_id,Page page){
		Map<String, Object> parameter=new HashMap<String, Object>();
		RepairProgress repairProgress=new RepairProgress();
		repairProgress.setRepair_id(repair_id);
		parameter.put("page", page);
		parameter.put("repairProgress", repairProgress);
		return repairProgressDao.getProgressesByPage(parameter);
	}
}
