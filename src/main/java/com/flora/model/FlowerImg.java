package com.flora.model;

import java.io.Serializable;
import java.util.Date;

public class FlowerImg extends BaseObject{
	private static final long serialVersionUID = 7484876054031890395L;
	private Integer id;
	private String imgName;
	private Integer flowerId;
	private Integer sortNum;
	private String  tag;
	private String status;
	private Date createTime;
	public FlowerImg(){
		
	}
	public FlowerImg(Integer flowerId, String tag, String imgName){
		this.flowerId = flowerId;
		this.tag = tag;
		this.imgName = imgName;
		this.sortNum = 1;
		this.status = "y";
		this.createTime = new Date();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public Integer getFlowerId() {
		return flowerId;
	}
	public void setFlowerId(Integer flowerId) {
		this.flowerId = flowerId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public Serializable realId() {
		return id;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
