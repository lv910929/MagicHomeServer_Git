package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Account;

public interface AccountDao{
	
	public void insertAccount(Account account);//新增

	public void editAccount(Account account);//修改

	public void removeAccount(int id);//删除

	public Account getAccountById(int id);//通过id获取用户

	public List<Account> getAllAccounts();//获取所有用户
	
	public Account getAccountByName(String name);
	
	public List<Account> getAccountsByPage(Map<String,Object> parameter);//分页查询
}
