package com.lencity.magichome.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.lencity.magichome.model.Repair;
import com.lencity.magichome.service.RepairService;

@SuppressWarnings("serial")
public class RepairManagerAction extends BaseAction{
	
	private RepairService repairService;
	
	private List<Repair> repairs;
	
	private Repair repair;
	
	private int id;
	
	private int device_type;
	
	private String account_name;
	
	private String problem_reason;
	
	private String begin_time;
	
	private String end_time;
	
	private int repair_status;

	public RepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(RepairService repairService) {
		this.repairService = repairService;
	}

	public int getRepair_status() {
		return repair_status;
	}

	public void setRepair_status(int repair_status) {
		this.repair_status = repair_status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Repair> getRepairs() {
		return repairs;
	}

	public void setRepairs(List<Repair> repairs) {
		this.repairs = repairs;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	/**
	 * 多条件分页查询
	 */
	public String list(){
		init();
		repairs=repairService.getRepairsByPage(account_name,repair_status, page);
		return "list";
	}
	/**
	 * 新增维修记录
	 */
	public String add(){
		Repair repair=new Repair();
		repair.setDevice_type(device_type);
		repair.setAccount_name(account_name);
		repair.setProblem_reason(problem_reason);
		repair.setBegin_time(DateFormat.getDateTimeInstance().format(new Date()));
		repair.setRepair_status(Repair.REPAIR_NOT);
		repairService.insertRepair(repair);
		addLog("新增了"+account_name+"的设备的维修记录");
		return "listAction";
	}
	/**
	 * 维修单例详情
	 */
	public String detail(){
		repair=repairService.getRepairByid(id);
		return "detail";
	}
	/**
	 * 开始维修
	 */
	public String begin(){
		repair=repairService.getRepairByid(id);
		if (repair.getRepair_status()==Repair.REPAIR_NOT) {
			repair.setRepair_status(Repair.REPAIR_ING);//正在维修
			repairService.updateRepair(repair);
		}
		addLog("修改了维修记录"+id+"的状态");
		repair=repairService.getRepairByid(id);
		return "detail";
	}
	/**
	 * 维修完成
	 */
	public String finish(){
		repair=repairService.getRepairByid(id);
		if (repair.getRepair_status()==Repair.REPAIR_ING) {
			repair.setEnd_time(DateFormat.getDateTimeInstance().format(new Date()));
			repair.setRepair_status(Repair.REPAIR_FINISH);
			repairService.updateRepair(repair);
		}
		addLog("修改了维修记录"+id+"的状态");
		repair=repairService.getRepairByid(id);
		return "detail";
	}
	/**
	 * 删除维修记录
	 */
	public String removeRepair(){
		addLog("删除了维修记录:"+id);
		repairService.removeRepair(id);
		init();
		repairs=repairService.getRepairsByPage(account_name,repair_status, page);
		return "list";
	}
}
