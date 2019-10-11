package com.app.service.Impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BookDao;
import com.app.dao.BorrowingBooksDao;
import com.app.dao.UserDao;
import com.app.model.Book;
import com.app.model.BorrowingBooks;
import com.app.model.User;
import com.app.service.BorrowingBooksService;

@Service
public class BorrowingBooksServiceImpl implements BorrowingBooksService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BorrowingBooksDao borrowingBooksDao;

	// 借书
	@Override
	public boolean borrowBook(int num, int userId) {
		try {
			Book book = bookDao.selectById(num);
			if (book == null)
				return false;
			User user = userDao.selectById(userId);
			if (user == null)
				return false;
			BorrowingBooks bbook = borrowingBooksDao.selectByBookId(num);
			if (bbook != null)
				return false;

			int ok = borrowingBooksDao.borrowBook(book.getBookId(), user.getUserId(), LocalDate.now(),
					LocalDate.now().plusDays(60));
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 还书
	@Override
	public boolean returnBook(int num) {
		try {
			Book book = bookDao.selectById(num);
			if (book == null)
				return false;
			int ok = borrowingBooksDao.returnBooks(num);
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
