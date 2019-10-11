package com.app.service.Impl;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BookDao;
import com.app.dao.BorrowingBooksDao;
import com.app.dao.UserDao;
import com.app.model.Book;
import com.app.model.BorrowingBooks;
import com.app.model.User;
import com.app.model.view.BorrowingBooksView;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BorrowingBooksDao borrowingBooksDao;

	// 验证用户是否存在
	@Override
	public boolean findUserByUserName(String userName) {
		try {
			User user = userDao.selectByName(userName);
			if (user == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 用户登录
	@Override
	public boolean userLogin(String userName, String password) {
		try {
			int ok = userDao.selectUser(userName, password);
			if (ok > 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 更新用户信息
	@Override
	public boolean updateUser(User user) {
		try {
			int ok = userDao.changeUser(user.getUserId(), user.getUserName(), user.getUserPwd(), user.getUserEmail());
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 通过用户id查找用户
	@Override
	public User findUserById(int id) {
		try {
			return userDao.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 添加用户
	@Override
	public boolean insertUser(User user) {
		try {
			int ok = userDao.insertRole(user.getUserName(), user.getUserPwd(), user.getUserEmail());
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 根据用户id删除用户
	@Override
	public boolean deleteUserById(int id) {
		try {
			int ok = userDao.deleteUser(id);
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 修改用户信息
	@Override
	public boolean changeUser(User user) {
		try {
			int ok = userDao.changeUser(user.getUserId(), user.getUserName(), user.getUserPwd(), user.getUserEmail());
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 查询用户借书
	@Override
	public List<BorrowingBooksView> userSelectByPageNum(int userId) {
		try {
			User user = userDao.selectById(userId);
			List<BorrowingBooks> borrowingBooksList = borrowingBooksDao.selectByUserId(userId);
			if (null == borrowingBooksList) {
				return null;
			}
			// 将数据库表对应的对象(Do)转化成视图层对象（View）
			List<BorrowingBooksView> res = new LinkedList<BorrowingBooksView>();
			for (BorrowingBooks borrowingBooks : borrowingBooksList) {
				Book book = bookDao.selectById(borrowingBooks.getBookId());
				BorrowingBooksView bbView = new BorrowingBooksView();
				bbView.setBook(book);
				bbView.setUser(user);
				DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				bbView.setDateOfBorrowing(borrowingBooks.getDate().format(dateTimeFormatter1));
				bbView.setDateOfReturn(borrowingBooks.getreturnTime().format(dateTimeFormatter1));
				res.add(bbView);
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
