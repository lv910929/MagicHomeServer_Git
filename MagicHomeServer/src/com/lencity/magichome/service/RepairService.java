package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.RepairDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Repair;

public class RepairService {
	
	private RepairDao repairDao;

	public RepairDao getRepairDao() {
		return repairDao;
	}

	public void setRepairDao(RepairDao repairDao) {
		this.repairDao = repairDao;
	}
	
	public void insertRepair(Repair repair){
		repairDao.insertRepair(repair);
	}
	//更新维修状态
	public void updateRepair(Repair repair){
		repairDao.updateRepair(repair);
	}

	public void removeRepair(int id){
		repairDao.removeRepair(id);
	}
	
	public List<Repair> getAllRepairs(){
		List<Repair> repairs=repairDao.getAllRepairs();
		
		return repairs;
	}
	
	public Repair getRepairByid(int id){
		
		Repair repair=repairDao.getRepairByid(id);
		return repair;
	}
	
	public List<Repair> getRepairsByaccount(String account){
		return repairDao.getRepairsByaccount(account);
	}
	
	public List<Repair> getRepairsByPage(String account_name,int repair_status,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		Repair repair=new Repair();
		repair.setAccount_name(account_name);
		repair.setRepair_status(repair_status);
		parameter.put("repair", repair);
		parameter.put("page", page);
		return repairDao.getRepairsByPage(parameter);
	}

}
