package com.app.model.view;

public class BookView {
	private Integer bookId; // 书籍id

	private String bookName; // 书名

	private String bookAuthor;// 作者

	private String bookPublish;// 出版社

	private String bookDescription;// 出版社

	private int isExist; // 是否可借

	public Integer getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookPublish() {
		return bookPublish;
	}

	public int getIsExist() {
		return isExist;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookPublish(String bookPublish) {
		this.bookPublish = bookPublish;
	}

	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
}
