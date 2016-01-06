package com.lencity.magichome.model;


public class Repair extends BaseModel{

	private static final long serialVersionUID = -502543409074419377L;

	public static final Integer REPAIR_NOT=1;
	
	public static final Integer REPAIR_ING=2;
	
	public static final Integer REPAIR_FINISH=3;
	
	public static final Integer IS_CONTROLER=1;
	
	public static final Integer NOT_CONTROLER=2;
	
	private int device_type;
	
	private String account_name;
	
	private String problem_reason;
	
	private String begin_time;
	
	private String end_time;
	
	private Integer repair_status;

	public Repair() {
		super();
	}

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getProblem_reason() {
		return problem_reason;
	}

	public void setProblem_reason(String problem_reason) {
		this.problem_reason = problem_reason;
	}

	public String getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getRepair_status() {
		return repair_status;
	}

	public void setRepair_status(Integer repair_status) {
		this.repair_status = repair_status;
	}
	

}
