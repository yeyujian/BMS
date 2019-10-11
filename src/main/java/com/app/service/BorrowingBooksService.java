package com.app.service;

public interface BorrowingBooksService {
	// 借书
	boolean borrowBook(int num, int userId);

	// 还书
	boolean returnBook(int num);

}
