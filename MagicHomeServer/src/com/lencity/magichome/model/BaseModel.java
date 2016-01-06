package com.lencity.magichome.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseModel implements Serializable {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
