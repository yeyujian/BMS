package com.app.service;

import com.app.model.Admin;

public interface AdminService {

	// 验证用户是否存在
	boolean adminIsExist(int id);

	// 管理员登陆
	Admin adminLogin(String name, String password);

}
