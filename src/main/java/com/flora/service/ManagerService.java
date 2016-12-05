package com.flora.service;

import com.flora.model.Manager;

public interface ManagerService {
	Manager getManager(String name, String password);
}
