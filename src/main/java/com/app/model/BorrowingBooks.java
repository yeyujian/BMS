package com.app.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BorrowingBooks {
	private Integer id;

	private Integer userId;

	private Integer bookId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getreturnTime() {
		return returnTime;
	}

	public void setreturnTime(LocalDate date) {
		this.returnTime = date;
	}
}