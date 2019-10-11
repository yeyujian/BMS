package com.app.model.view;

import com.app.model.Book;
import com.app.model.User;

public class BorrowingBooksView {
	private User user;
	private Book book; // 借阅书籍
	private String dateOfBorrowing; // 借书日期
	private String dateOfReturn; // 还书日期

	public void setBook(Book book) {
		this.book = book;
	}

	public void setDateOfBorrowing(String dateOfBorrowing) {
		this.dateOfBorrowing = dateOfBorrowing;
	}

	public void setDateOfReturn(String dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public Book getBook() {
		return book;
	}

	public String getDateOfBorrowing() {
		return dateOfBorrowing;
	}

	public String getDateOfReturn() {
		return dateOfReturn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
