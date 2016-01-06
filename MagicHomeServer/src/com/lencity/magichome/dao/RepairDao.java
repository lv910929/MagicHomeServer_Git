package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Repair;

public interface RepairDao {
	
	public void insertRepair(Repair repair);
	//更新维修状态
	public void updateRepair(Repair repair);

	public void removeRepair(int id);
	
	public List<Repair> getAllRepairs();
	
	public Repair getRepairByid(int id);
	
	public List<Repair> getRepairsByaccount(String account);
	
	public List<Repair> getRepairsByPage(Map<String, Object> parameter);
}
