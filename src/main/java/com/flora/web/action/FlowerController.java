package com.flora.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flora.model.Flower;

@Controller
public class FlowerController extends BaseController{
	@RequestMapping("/admin/flower/list.xhtml")
	public String list(ModelMap model, HttpServletRequest request) {
		Flower f = daoService.getObject(Flower.class, 1);
		return "admin/flower/list.vm";
	}
	@RequestMapping("/admin/flower/modify.xhtml")
	public String modify(Integer id, ModelMap model, HttpServletRequest request) {
		if(id!=null){
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
}
