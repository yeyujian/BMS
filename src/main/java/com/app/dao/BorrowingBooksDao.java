package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.app.model.BorrowingBooks;

@Mapper
@Repository
public interface BorrowingBooksDao {
	static final String TABLE_NAME = "borrowingbooks";
	static final String FIELDS = "id,book_id,user_id,date,return_time";

	@Select({ "select", "id,book_id as bookId,user_id as userId,user_id as userId,date,return_time as returnTime",
			"from", TABLE_NAME, "where user_id=#{id}" })
	public List<BorrowingBooks> selectByUserId(int id);

	@Select({ "select", "id,book_id as bookId,user_id as userId,user_id as userId,date,return_time as returnTime",
			"from", TABLE_NAME, "where book_id=#{id}" })
	public BorrowingBooks selectByBookId(int id);

	@Insert({
			"insert into borrowingbooks(book_id,user_id,date,return_time) values (#{book_id}, #{user_id},#{date},#{return_time})" })
	public int borrowBook(@Param("book_id") int book_id, @Param("user_id") int user_id,
			@Param("date") LocalDate localDate, @Param("return_time") LocalDate return_time);

	@Update({ "update borrowingbooks set return_time=#{return_time} where id=#{id}" })
	public int changeReturnTime(int id);

	@Delete({ "delete from borrowingbooks where book_id = #{id}" })
	public int returnBooks(int id);
}
