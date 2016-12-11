package com.flora.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flora.Config;
import com.flora.model.Flower;
import com.flora.util.BindUtils;
import com.flora.util.StringUtil;
import com.flora.web.util.WebUtils;

@Controller
public class FlowerController extends BaseController {
	@RequestMapping("/admin/flower/list.xhtml")
	public String list(ModelMap model, HttpServletRequest request) {
		Flower f = daoService.getObject(Flower.class, 1);
		return "admin/flower/list.vm";
	}

	@RequestMapping("/admin/flower/modify.xhtml")
	public String modify(Integer id, ModelMap model, HttpServletRequest request) {
		if (id != null) {
			model.put("flower", daoService.getObject(Flower.class, id));
		}
		return "admin/flower/modifyFlower.vm";
	}

	@RequestMapping("/admin/flower/saveFlower.xhtml")
	public String place(Integer id,  ModelMap model, HttpServletRequest request) {
		Flower flower = new Flower();
		if(id!=null){
			flower = daoService.getObject(Flower.class, id);
		}else {
			flower.setStatus("y");
			flower.setAddTime(new Date());
		}
		BindUtils.bindData(flower, WebUtils.getRequestMap(request));
		daoService.saveObject(flower);
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
