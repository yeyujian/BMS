package com.app.service;


import com.app.model.User;

public interface AdminUserService {
	// 验证用户是否存在
	boolean userIsExist(int id);

	// 管理员登陆
	User adminUserLogin(String name, String password);
}
