package com.app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BookDao;
import com.app.dao.BorrowingBooksDao;
import com.app.dao.UserDao;
import com.app.model.Book;
import com.app.model.BorrowingBooks;
import com.app.model.view.BookView;
import com.app.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BorrowingBooksDao borrowingBooksDao;

	// 录入新书
	@Override
	public boolean addBook(Book book) {
		try {
			int ok = bookDao.insertBook(book.getBookName(), book.getBookAuthor(), book.getBookPublish(),
					book.getBookCategory(), book.getBookPrice(), book.getBookIntroduction());
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除书
	@Override
	public boolean delectBookById(int id) {
		try {
			int ok = bookDao.deleteBook(id);
			if (ok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 修改书
	@Override
	public boolean changeBook(Book book) {
		try {
			int ok = bookDao.changeBook(book.getBookId(), book.getBookName(), book.getBookAuthor(),
					book.getBookPublish(), book.getBookCategory(), book.getBookPrice(), book.getBookIntroduction());
			if (ok > 0) {
				return true;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 查询书状态
	@Override
	public BookView findBookById(int id) {
		try {
			Book book = bookDao.selectById(id);
			BorrowingBooks bbook = borrowingBooksDao.selectByBookId(id);
			if (book == null) {
				return null;
			}

			BookView bview = new BookView();
			bview.setBookAuthor(book.getBookAuthor());
			bview.setBookId(id);
			;
			bview.setBookName(book.getBookName());
			bview.setBookPublish(book.getBookPublish());
			bview.setBookDescription(book.getBookIntroduction());

			if (bbook == null) {
				bview.setIsExist(0);
			} else {
				bview.setIsExist(1);
			}
			return bview;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
