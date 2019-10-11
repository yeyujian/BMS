package com.app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AdminDao;
import com.app.model.Admin;
import com.app.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	// 验证用户是否存在
	@Override
	public boolean adminIsExist(int id) {
		try {
			Admin admin = adminDao.selectById(id);
			if (admin == null) {
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
	public Admin adminLogin(String name, String password) {
		try {
			return adminDao.selectAdmin(name, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
