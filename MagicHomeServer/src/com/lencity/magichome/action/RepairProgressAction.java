package com.lencity.magichome.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.lencity.magichome.model.RepairProgress;
import com.lencity.magichome.service.RepairProgressService;

@SuppressWarnings("serial")
public class RepairProgressAction extends BaseAction{
	
	private RepairProgressService repairProgressService;
	
	private List<RepairProgress> repairProgresses;
	
	private int id;
	
	private int repair_id;
	
	private String repair_time;
	
	private String repair_info;

	public RepairProgressService getRepairProgressService() {
		return repairProgressService;
	}

	public void setRepairProgressService(RepairProgressService repairProgressService) {
		this.repairProgressService = repairProgressService;
	}

	public List<RepairProgress> getRepairProgresses() {
		return repairProgresses;
	}

	public void setRepairProgresses(List<RepairProgress> repairProgresses) {
		this.repairProgresses = repairProgresses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRepair_id() {
		return repair_id;
	}

	public void setRepair_id(int repair_id) {
		this.repair_id = repair_id;
	}

	public String getRepair_time() {
		return repair_time;
	}

	public void setRepair_time(String repair_time) {
		this.repair_time = repair_time;
	}

	public String getRepair_info() {
		return repair_info;
	}

	public void setRepair_info(String repair_info) {
		this.repair_info = repair_info;
	}
	
	public String list(){
		init();
		repairProgresses=repairProgressService.getProgressesByPage(repair_id, page);
		return "list";
	}
	
	public String add(){
		RepairProgress repair_Progress=new RepairProgress();
		repair_Progress.setRepair_id(repair_id);
		repair_Progress.setRepair_time(DateFormat.getDateTimeInstance().format(new Date()));
		repair_Progress.setRepair_info(repair_info);
		repairProgressService.insertProgress(repair_Progress);
		init();
		repairProgresses=repairProgressService.getProgressesByPage(repair_id, page);
		addLog("新增了维修记录"+repair_id+"的维修进度");
		return "list";
	}

}
