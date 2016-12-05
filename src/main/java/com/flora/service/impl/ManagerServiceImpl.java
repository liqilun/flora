package com.flora.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flora.model.Manager;
import com.flora.service.ManagerService;


@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl implements ManagerService {
	public Manager getManager(String username, String password) {
		String hql = "from Manager where username=? and password=?";
		List<Manager> userList = baseDao.findByHql(hql, username, password);
		if(userList!=null && userList.size()==1) return userList.get(0);
		return null;
	}
}

