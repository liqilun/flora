package com.flora.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flora.model.FlowerAd;
import com.flora.model.FlowerImg;
import com.flora.util.BindUtils;
import com.flora.util.PageUtil;
import com.flora.web.util.WebUtils;

@Controller
public class FlowerAdController extends BaseController {
	private DetachedCriteria query(String tag){
		DetachedCriteria query = DetachedCriteria.forClass(FlowerAd.class);
		query.add(Restrictions.eq("tag", tag));
		return query;
	}
	@RequestMapping("/admin/flowerAd/list.xhtml")
	public String list(String tag, Integer pageNo, ModelMap model) {
		if(pageNo==null || pageNo<0){
			pageNo = 0;
		}
		int maxnum = 10;
		int from = pageNo*maxnum;
		DetachedCriteria query = query(tag);
		query.addOrder(Order.desc("id"));
		List<FlowerAd> flowerList = daoService.findByCriteria(query, from, maxnum);
		
		query = query(tag);
		query.setProjection(Projections.rowCount());
		List list = daoService.findByCriteria(query);
		int rowsCount = Integer.valueOf(list.get(0)+"");
		PageUtil pageUtil = new PageUtil(rowsCount, maxnum, pageNo, "admin/flowerad/list.xhtml");
		Map params = new HashMap();
		pageUtil.initPageInfo(params);
		model.put("floweradList",flowerList);
		model.put("pageUtil",pageUtil);
		return "admin/flowerad/list.vm";
	}

	@RequestMapping("/admin/flowerAd/modify.xhtml")
	public String modify(Integer id, ModelMap model) {
		if (id != null) {
			model.put("flowerad", daoService.getObject(FlowerAd.class, id));
			List<FlowerImg> imgList = daoService.getObjectListByField(FlowerImg.class, "flowerId", id);
			Map<String, FlowerImg> imgMap = new HashMap<String, FlowerImg>();
			for(FlowerImg img : imgList){
				imgMap.put(img.getTag(), img);
			}
			model.put("imgMap",imgMap);
		}
		return "admin/flowerad/modifyFlowerAd.vm";
	}
	@RequestMapping("/admin/flowerAd/save.xhtml")
	public String place(Integer id,  ModelMap model, HttpServletRequest request) {
		FlowerAd ad = new FlowerAd();
		if(id!=null){
			ad = daoService.getObject(FlowerAd.class, id);
		}else {
			ad.setStatus("y");
			ad.setCreateTime(new Date());
		}
		BindUtils.bindData(ad, WebUtils.getRequestMap(request));
		daoService.saveObject(ad);
		return writeJsonSuccess(model);
	}
}
