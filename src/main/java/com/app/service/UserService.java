package com.app.service;

import java.util.List;

import com.app.model.User;
import com.app.model.view.BorrowingBooksView;

public interface UserService {
	// 验证用户是否存在
	boolean findUserByUserName(String userName);

	// 用户登录
	boolean userLogin(String userName, String password);

	// 更新用户信息
	boolean updateUser(User user);

	// 通过用户id查找用户
	User findUserById(int id);

	// 添加用户
	boolean insertUser(User user);

	// 根据用户id删除用户
	boolean deleteUserById(int id);

	// 修改用户信息
	boolean changeUser(User user);

	// 查询用户借书
	List<BorrowingBooksView> userSelectByPageNum(int userId);

}
