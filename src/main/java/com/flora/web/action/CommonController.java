package com.flora.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController extends BaseController{
	@RequestMapping("/login.xhtml")
	public String login(ModelMap model, HttpServletRequest request) {
		return "login.vm";
	}
}
