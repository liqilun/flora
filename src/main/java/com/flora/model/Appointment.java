package com.flora.model;

import java.io.Serializable;
import java.util.Date;

public class Appointment extends BaseObject{
	private static final long serialVersionUID = 8306498245301412859L;
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
