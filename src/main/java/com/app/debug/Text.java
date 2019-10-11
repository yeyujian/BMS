package com.app.debug;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.BookDao;
import com.app.dao.BorrowingBooksDao;
import com.app.dao.UserDao;
import com.app.model.Admin;
import com.app.model.Book;
import com.app.model.BorrowingBooks;
import com.app.model.User;
import com.app.service.BorrowingBooksService;

@RestController
public class Text {
	@Autowired
	private com.app.dao.AdminDao adminDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BorrowingBooksDao borrowingBooksDao;

	@Resource
	private BorrowingBooksService borrowingBooksService;

	@RequestMapping("/ff")
	public String index() {

		try {
			Admin one = adminDao.selectById(0);
//			return one.toString();
			if (one != null) {
				return one.toString();
			} else {
				return "null";
			}

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/login1")
	public int login() {

		try {
			int one = 0;
			return one;

		} catch (Exception e) {
			return 1111;
		}
	}

	@RequestMapping("/insert2")
	public String insert() {

		try {

			return "1";

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/*
	 * 
	 * //设置指定日期的时间对象: LocalDate appoint = LocalDate.parse("2018-12-07");
	 * 
	 * //计算两个日期相差多少天: long differ = appoint.toEpochDay()-now.toEpochDay() // differ
	 * 90
	 * 
	 */
	@RequestMapping("/borrowbook")
	public String borrowbook() {
		int num = 1, userId = 1;
		try {
			Book book = bookDao.selectById(num);
			if (book == null)
				return "1";
			User user = userDao.selectById(userId);
			if (user == null)
				return "2";
			BorrowingBooks bbook = borrowingBooksDao.selectByBookId(num);
			if (bbook != null)
				return "3";

			int ok = borrowingBooksDao.borrowBook(book.getBookId(), user.getUserId(), LocalDate.now(),
					LocalDate.now().plusDays(60));
			if (ok > 0) {
				return "4";
			}
			return "5";
		} catch (Exception e) {
			e.printStackTrace();
			return "6";
		}
	}

	@RequestMapping("/returnbook")
	public String returnbook() {
		int num = 1;
		try {
			Book book = bookDao.selectById(num);
			if (book == null)
				return "1";
			int ok = borrowingBooksDao.returnBooks(num);
			if (ok > 0) {
				return "2";
			}
			return "3";
		} catch (Exception e) {
			e.printStackTrace();
			return "4";
		}
	}
}