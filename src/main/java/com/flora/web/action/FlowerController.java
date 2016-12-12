package com.flora.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flora.Config;
import com.flora.model.Flower;
import com.flora.model.FlowerImg;
import com.flora.util.BindUtils;
import com.flora.util.StringUtil;
import com.flora.web.util.WebUtils;

@Controller
public class FlowerController extends BaseController {
	@RequestMapping("/admin/flower/list.xhtml")
	public String list(ModelMap model) {
		List<Flower> flowerList = daoService.getObjectList(Flower.class, "id", false, 0, 100);
		model.put("flowerList",flowerList);
		return "admin/flower/list.vm";
	}

	@RequestMapping("/admin/flower/modify.xhtml")
	public String modify(Integer id, ModelMap model) {
		if (id != null) {
			model.put("flower", daoService.getObject(Flower.class, id));
			List<FlowerImg> imgList = daoService.getObjectListByField(FlowerImg.class, "flowerId", id);
			Map<String, FlowerImg> imgMap = new HashMap<String, FlowerImg>();
			for(FlowerImg img : imgList){
				imgMap.put(img.getTag(), img);
			}
			model.put("imgMap",imgMap);
		}
		return "admin/flower/modifyFlower.vm";
	}
	@RequestMapping("/admin/flower/flowerImgList.xhtml")
	public String flowerImgList(Integer flowerId, ModelMap model) {
		List<FlowerImg> imgList = daoService.getObjectListByField(FlowerImg.class, "flowerId", flowerId);
		Map<String, FlowerImg> imgMap = new HashMap<String, FlowerImg>();
		for(FlowerImg img : imgList){
			imgMap.put(img.getTag(), img);
		}
		Flower flower = daoService.getObject(Flower.class, flowerId);
		model.put("imgMap",imgMap);
		model.put("flower",flower);
		model.put("imgList",imgList);
		return "admin/flower/flowerImg.vm";
	}
	@RequestMapping("/admin/flower/setLogo.xhtml")
	public String setLogo(Integer id, ModelMap model) {
		FlowerImg img = daoService.getObject(FlowerImg.class, id);
		Flower flower = daoService.getObject(Flower.class, img.getFlowerId());
		flower.setLogo(img.getImgName());
		daoService.saveObject(flower);
		return writeJsonSuccess(model);
	}
	@RequestMapping("/admin/flower/setflowerImgSortNum.xhtml")
	public String setflowerImgSortNum(Integer id, Integer sortNum, ModelMap model, HttpServletRequest request) {
		FlowerImg img = daoService.getObject(FlowerImg.class, id);
		img.setSortNum(sortNum);
		daoService.saveObject(img);
		return writeJsonSuccess(model);
	}
	@RequestMapping("/admin/flower/saveFlower.xhtml")
	public String place(Integer id,   ModelMap model, HttpServletRequest request) {
		Flower flower = new Flower();
		if(id!=null){
			flower = daoService.getObject(Flower.class, id);
		}else {
			flower.setStatus("y");
			flower.setAddTime(new Date());
		}
		BindUtils.bindData(flower, WebUtils.getRequestMap(request));
		if(StringUtils.isBlank(flower.getName())){
			return writeJsonError(model, "请输入植物的中文名称");
		}
		if(StringUtils.isBlank(flower.getContent())){
			return writeJsonError(model, "请输入植物的简介");
		}
		daoService.saveObject(flower);
		if(id!=null){
			daoService.removeObjectList(daoService.getObjectListByField(FlowerImg.class, "flowerId", id));
		}
		daoService.saveObject(new FlowerImg(flower.getId(), "logo", flower.getLogo()));
		for(int i=1;i<10;i++){
			String img = request.getParameter("img"+i);
			if(StringUtils.isNotBlank(img)){
				daoService.saveObject(new FlowerImg(flower.getId(), "img"+i, img));
			}
		}
		return writeJsonSuccess(model);
	}

	@RequestMapping("/upload.xhtml")
	@ResponseBody
	public String uploadImage(@RequestParam MultipartFile file) throws IOException {
		if (file == null) {
			return "<script type=\"text/javascript\">parent.callback('请选择需要上传的图片')</script>";
		}
		if (file.getSize() > 1572864) { // 1024*1024*1.5
			return "<script type=\"text/javascript\">parent.callback('上传图片太大')</script>";
		}
		String imgPath = file.getOriginalFilename();
		// imgPath为原文件名
		int idx = imgPath.lastIndexOf(".");
		// 文件后缀
		String ext = imgPath.substring(idx);
		String fileName = StringUtil.getRandomString(10).toLowerCase() + ext;
		String fullName = Config.UPLOAD_PATH + fileName;
		File newfile = new File(fullName);
		file.transferTo(newfile);
		return "<script type=\"text/javascript\">parent.callback('" + fileName + "')</script>";
	}
}
