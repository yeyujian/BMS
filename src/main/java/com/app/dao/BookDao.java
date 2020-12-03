package com.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.app.model.Book;

@Mapper
@Repository
public interface BookDao {
	static final String TABLE_NAME = "book";
	static final String FIELDS = "book_id,book_name,book_author,book_publish,book_category,book_price,book_introduction";

	@Select({ "select",
			"book_id as BookId,book_name as bookName,book_author as bookAuthor,book_publish as bookPublish,book_category as bookCategory,book_price as bookPrice,book_introduction as bookIntroduction ",
			"from", TABLE_NAME, "where book_id=#{id}" })
	public Book selectById(int id);

	@Select({ "select",
			"book_id as BookId,book_name as bookName,book_author as bookAuthor,book_publish as bookPublish,book_category as bookCategory,book_price as bookPrice,book_introduction as bookIntroduction ",
			"from", TABLE_NAME, "where book_category=#{category}" })
	public List<Book> selectByCategory(String category);

	@Select({ "select",
			"book_id as BookId,book_name as bookName,book_author as bookAuthor,book_publish as bookPublish,book_category as bookCategory,book_price as bookPrice,book_introduction as bookIntroduction ",
			"from", TABLE_NAME, "where book_name like CONCAT('%',#{name},'%') " })
	public List<Book> selectByName(String name);

	@Insert({ "insert into book(", "book_name,book_author,book_publish,book_category,book_price,book_introduction",
			") values (#{name}, #{author},#{publish},#{category},#{price},#{introduction})" })
	public int insertBook(@Param("name") String name, @Param("author") String autor, @Param("publish") String publish,
			@Param("category") String category, @Param("price") double price,
			@Param("introduction") String introduction);

	@Update("update book set book_name=#{book_name},book_author=#{book_author},book_publish=#{book_publish},book_category=#{book_category},book_price=#{book_price},book_introduction=#{book_introduction} where Book_id=#{Book_id}")
	public int changeBook(@Param("Book_id") int Book_id, @Param("book_name") String name,
			@Param("book_author") String author, @Param("book_publish") String publish,
			@Param("book_category") String category, @Param("book_price") double price,
			@Param("book_introduction") String introduction);

	@Delete({ "delete from book where book_id = #{id}" })
	public int deleteBook(int id);
}
