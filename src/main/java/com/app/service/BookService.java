package com.app.service;

import com.app.model.Book;
import com.app.model.view.BookView;

public interface BookService {
	// 录入新书
	boolean addBook(Book book);

	// 删除书
	boolean delectBookById(int id);

	// 修改书
	boolean changeBook(Book book);

	// 查询书状态
	BookView findBookById(int id);
}
