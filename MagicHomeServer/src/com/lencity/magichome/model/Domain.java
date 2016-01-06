package com.lencity.magichome.model;

public class Domain extends BaseModel{

	private static final long serialVersionUID = 5546981852162025124L;

	private String domain_name;
	
	private String sn_code;

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getSn_code() {
		return sn_code;
	}

	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}
	
	
}
