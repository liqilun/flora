package com.flora.model;

import java.io.Serializable;
import java.util.Date;

public class Case extends BaseObject{
	private static final long serialVersionUID = -6206552940082740092L;
	private Integer id;
	private String name;
	private Date addTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Override
	public Serializable realId() {
		return id;
	}
}
