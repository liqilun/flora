package com.flora.model;

import java.io.Serializable;
import java.util.Date;

public class Flower extends BaseObject{
	/*图片	最多可上传10张，可设置封面图，支持图片排序	*/
	private Integer id;
	//名称/中文学名
	private String name;
	//文本输入，100字以内
	private String ldName;
	//别称
	private String aliasName;
	//英文名
	private String englishName;
	//植株性质	草本植物、木本植物、水生植物、室内植物、水培植物	多选
	private String attribute;
	//类群	苔藓植物、蕨类植物、裸子植物、被子植物	多选
	private String grp;
	//观赏部位	多肉植物、观花植物、观叶植物	多选
	private String viewArea;
	//适宜环境	室内、室外	单选
	private Integer fitEnv;
	//主要价值	观赏、吸尘、空气净化、	多选
	private String worth;
	//尺寸 多选
	private String size;
	//养护难易	易养、较易养、较难养、难养、	单选
	private Integer curingEasy;
	//装饰风格	东南亚、地中海、北欧、中式、日式、简约	多选*/
	private String zsStyle;
	//植物简介 
	private String content;
	private String logo;
	private String images;
	//水
	private String water;
	//土壤
	private String soil;
	//湿度
	private String humidity;
	//施肥	
	private String feed;
	//适宜温度
	private String temperature;
	//不适宜温度
	private String unTemperature;
	//花语
	private String flowerWord;
	//花期
	private String flowerDate;
	//光照要求
	private String sunNeed;
	//适宜摆放空间
	private String space;
	//生长习性
	private String habit;
	//产地分布
	private String producer;
	//主要品种
	private String types;
	//门类科属
	private String category;
	
	private Date addTime;
	
	private String status;
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

	public String getLdName() {
		return ldName;
	}

	public void setLdName(String ldName) {
		this.ldName = ldName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public String getViewArea() {
		return viewArea;
	}

	public void setViewArea(String viewArea) {
		this.viewArea = viewArea;
	}

	public Integer getFitEnv() {
		return fitEnv;
	}

	public void setFitEnv(Integer fitEnv) {
		this.fitEnv = fitEnv;
	}

	public String getWorth() {
		return worth;
	}

	public void setWorth(String worth) {
		this.worth = worth;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getCuringEasy() {
		return curingEasy;
	}

	public void setCuringEasy(Integer curingEasy) {
		this.curingEasy = curingEasy;
	}

	public String getZsStyle() {
		return zsStyle;
	}

	public void setZsStyle(String zsStyle) {
		this.zsStyle = zsStyle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getWater() {
		return water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	public String getSoil() {
		return soil;
	}

	public void setSoil(String soil) {
		this.soil = soil;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getUnTemperature() {
		return unTemperature;
	}

	public void setUnTemperature(String unTemperature) {
		this.unTemperature = unTemperature;
	}

	public String getFlowerWord() {
		return flowerWord;
	}

	public void setFlowerWord(String flowerWord) {
		this.flowerWord = flowerWord;
	}

	public String getFlowerDate() {
		return flowerDate;
	}

	public void setFlowerDate(String flowerDate) {
		this.flowerDate = flowerDate;
	}

	public String getSunNeed() {
		return sunNeed;
	}

	public void setSunNeed(String sunNeed) {
		this.sunNeed = sunNeed;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getHabit() {
		return habit;
	}

	public void setHabit(String habit) {
		this.habit = habit;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
