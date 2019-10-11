package com.app.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.app.model.User;

@Mapper
@Repository
public interface UserDao {
	static final String TABLE_NAME = "user";
	static final String FIELDS = "user_id,user_name,user_pwd,user_email";

	@Select({ "select", "user_id as userId,user_name as userName,user_pwd as userPwd,user_email as userEmail", "from",
			TABLE_NAME, "where user_id=#{id}" })
	public User selectById(int id);

	@Select({ "select", "user_id as userId,user_name as userName,user_pwd as userPwd,user_email as userEmail", "from",
			TABLE_NAME, "where user_name=#{name}" })
	public User selectByName(String name);

	@Select({ "select", "user_id", "from", TABLE_NAME, "where user_name=#{User_name} and user_pwd=#{User_pass}" })
	public int selectUser(@Param("User_name") String User_name, @Param("User_pass") String User_pass);

	@Insert({ "insert into user(user_name,user_pwd ,user_email) values (#{User_name}, #{User_pass},#{user_email})" })
	public int insertRole(@Param("User_name") String User_name, @Param("User_pass") String User_pass,
			@Param("user_email") String user_email);

	@Update({
			"update user set user_name=#{user_name},user_pwd=#{user_pwd} ,user_email=#{user_email} where user_id=#{user_id}" })
	public int changeUser(@Param("user_id") int user_id, @Param("user_name") String User_name,
			@Param("user_pwd") String User_pass, @Param("user_email") String user_email);

	@Delete("delete from user where user_id = #{id}")
	public int deleteUser(int id);
}
