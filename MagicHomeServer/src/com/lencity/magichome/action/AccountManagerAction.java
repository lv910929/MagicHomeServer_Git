package com.lencity.magichome.action;

import java.util.List;

import com.lencity.magichome.model.Account;
import com.lencity.magichome.model.Device;
import com.lencity.magichome.service.AccountService;
import com.lencity.magichome.service.DeviceService;

@SuppressWarnings("serial")
public class AccountManagerAction extends BaseAction{

	private AccountService accountService;
	
	private Account account;
	
	private List<Account> accounts;
	
	private DeviceService deviceService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	private int id;
	
	private String accountName;
	
	private String password;
	
	private String mobile;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 分页获取账号
	 */
	public String list(){
			
		init();
		accounts=accountService.getAccountsByPage(accountName, mobile, page);
		return "list";
	}
	
	/**
	 * 修改账号信息
	 * @throws Exception 
	 */
	public String resetPassword() throws Exception{
		
		accountService.resetPassword(id);
		init();
		accounts=accountService.getAccountsByPage(accountName, mobile, page);
		Account account=accountService.getAccountById(id);
		addLog("重置了用户："+account.getAccountName()+"的密码！");
		return "list";
	}
    /**
     * 删除账号
     * @return
     */
    public String remove() {  
    	
    	Account account=accountService.getAccountById(id);
    	addLog("删除了用户:"+account.getAccountName());
    	addLog("删除了用户:"+account.getAccountName()+"的中控设备");
    	Device device = deviceService.getDeviceByAccount(account.getAccountName());
    	deviceService.removeDevice(device.getId());
		accountService.removeAccount(id);
		init();
		accounts=accountService.getAccountsByPage(accountName, mobile, page);
		return "list";
    }
    
}
