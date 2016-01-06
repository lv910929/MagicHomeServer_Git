package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.AccountDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Account;
import com.lencity.magichome.util.Md5;

public class AccountService {

	private AccountDao accountDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void insertAccount(Account account) throws Exception {
		
		account.setPassword(Md5.md5(account.getAccountName() + account.getPassword()));
		accountDao.insertAccount(account);
	}

	public void editAccount(Account account) {

		accountDao.editAccount(account);
	}

	public void removeAccount(int id) {

		accountDao.removeAccount(id);
	}

	public Account getAccountById(int id) {
		Account result = accountDao.getAccountById(id);
		return result;
	}
	
	public Account getAccountByName(String name){
		Account result=accountDao.getAccountByName(name);
		
		return result;
	}

	public List<Account> getAllAccounts() {

		List<Account> accounts = accountDao.getAllAccounts();
		
		return accounts;
	}
	/**
	 * 分页查询
	 */
	public List<Account> getAccountsByPage(String accountName,String mobile,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		Account account=new Account();
		account.setAccountName(accountName);
		account.setMobile(mobile);
		parameter.put("account", account);
		parameter.put("page", page);
		return accountDao.getAccountsByPage(parameter);
	}
	
	public Account login(String name,String password) throws Exception{
		Account account=accountDao.getAccountByName(name);
		if (account!=null) {
			password=Md5.md5(name+password);
			if (account.getPassword().equals(password)) {
				return account;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	/**
	 * 修改密码
	 * @throws Exception 
	 */
	public Account changePassword(String name,String newPassword) throws Exception{
		
		Account account=accountDao.getAccountByName(name);
		if (null != account){
			
			account.setPassword(Md5.md5(account.getAccountName() + newPassword));
			accountDao.editAccount(account);
			account=accountDao.getAccountByName(name);
		}
		return account;
	}
	/**
	 * 重置密码
	 * @throws Exception 
	 */
	public void resetPassword(int id) throws Exception{
		
		Account account=accountDao.getAccountById(id);
		if (null != account){
			account.setPassword(Md5.md5(account.getAccountName() + "123456"));
			accountDao.editAccount(account);
		}
	}
	/**
	 * 验证是否存在
	 */
	public boolean isExist(String name){
		
		if (accountDao.getAccountByName(name)!=null) {//说明已存在
			return true;
		}else {
			return false;
		}
		
	}

}
