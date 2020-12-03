package com.app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.model.Admin;
import com.app.model.User;
import com.app.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private UserDao userDao;

	// 验证用户是否存在
	@Override
	public boolean userIsExist(int id) {
		try {
			User user = userDao.selectById(id);
			if (user == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 管理员登陆
	@Override
	public User adminUserLogin(String name, String password) {
		try {
			int id = userDao.selectUser(name, password);
			if (id > 0) {
				return userDao.selectById(id);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
