package com.lencity.magichome.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lencity.magichome.model.Repair;
import com.lencity.magichome.model.RepairProgress;
import com.lencity.magichome.service.AccountService;
import com.lencity.magichome.service.DomainService;
import com.lencity.magichome.service.RepairProgressService;
import com.lencity.magichome.service.RepairService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AndroidAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	HttpServletRequest request;

	HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private RepairService repairService;
	
	private RepairProgressService repairProgressService;

	private AccountService accountService;
	
	private DomainService domainService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	public RepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(RepairService repairService) {
		this.repairService = repairService;
	}

	public RepairProgressService getRepairProgressService() {
		return repairProgressService;
	}

	public void setRepairProgressService(RepairProgressService repairProgressService) {
		this.repairProgressService = repairProgressService;
	}
	
	public DomainService getDomainService() {
		return domainService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	private int id;
	
	private String name;
	
	private String password;
	
	private String mobile;
	
	private String account_name;
	
	private String problem_reason;
	
	private String begin_time;
	
	private String end_time;
	
	private int repair_status;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getRepair_status() {
		return repair_status;
	}

	public void setRepair_status(int repair_status) {
		this.repair_status = repair_status;
	}

	/**
     * 查询维修记录
     */
    public void checkRepairs(){
    	try {
    		this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			
			account_name=request.getParameter("account");
			
			PrintWriter pw = response.getWriter();
			Gson gson = new GsonBuilder().create();
			List<Repair> repairs=repairService.getRepairsByaccount(account_name);
			String json=gson.toJson(repairs);
			pw.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 查看维修进度
     */
    public void checkRepairProgress(){
    	try {
    		this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			
			String id=request.getParameter("repairId");
			PrintWriter pw = response.getWriter();
			Gson gson = new GsonBuilder().create();
			List<RepairProgress> repairProgresses=repairProgressService.getProgressesByRepair_id(Integer.parseInt(id));
			String json=gson.toJson(repairProgresses);
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
