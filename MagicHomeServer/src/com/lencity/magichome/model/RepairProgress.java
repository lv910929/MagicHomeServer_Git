package com.lencity.magichome.model;


public class RepairProgress extends BaseModel{

	private static final long serialVersionUID = 5423203524150974921L;
	
	private int repair_id;
	
	private String repair_time;
	
	private String repair_info;

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
	
	

}
