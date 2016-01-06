package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;
import com.lencity.magichome.model.RepairProgress;

public interface RepairProgressDao {
	
	public List<RepairProgress> getProgressesByRepair_id(int repair_id);
	
	public List<RepairProgress> getProgressesByPage(Map<String, Object> parameter);
	
	public void insertProgress(RepairProgress repairProgress);

}
