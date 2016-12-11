package com.flora.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flora.Config;
import com.flora.model.Flower;

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
	public String place(Flower flower, ModelMap model) {
		flower.setStatus("y");
		flower.setAddTime(new Date());
		daoService.saveObject(flower);
		return writeJsonSuccess(model);
	}

	@RequestMapping("/upload.xhtml")
	@ResponseBody
	public String uploadImage(MultipartFile file) throws IOException {
		if (file == null) {
			return "<script type=\"text/javascript\">parent.callback('请选择需要上传的图片')</script>";
		}
		if (file.getSize() > 1572864) { // 1024*1024*1.5
			return "<script type=\"text/javascript\">parent.callback('上传图片太大')</script>";
		}
		String imgPath = file.getName();
		// imgPath为原文件名
		int idx = imgPath.lastIndexOf(".");
		// 文件后缀
		String ext = imgPath.substring(idx);
		String fileName = "flora/" + com.flora.util.StringUtil.getRandomString(8).toLowerCase() + ext;
		File newfile = new File(Config.UPLOAD_PATH + fileName);
		file.transferTo(newfile);
		return "<script type=\"text/javascript\">parent.callback('" + fileName + "')</script>";
	}
}
