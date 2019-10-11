package com.app.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.app.model.Admin;

@Mapper
@Repository
public interface AdminDao {
	static final String TABLE_NAME = "admin";
	static final String FIELDS = "admin_id,admin_name,admin_pwd,system_admin";

	@Select({ "select", "admin_id as adminId,admin_name as adminName,admin_pwd as adminPwd,system_admin as Systemadmin",
			"from", TABLE_NAME, "where admin_id=#{id}" })
	public Admin selectById(int id);

	@Select({ "select", "admin_id as adminId,admin_name as adminName,admin_pwd as adminPwd,system_admin as Systemadmin",
			"from", TABLE_NAME, "where admin_name=#{User_name} and admin_pwd=#{User_pass}" })
	public Admin selectAdmin(@Param("User_name") String User_name, @Param("User_pass") String User_pass);

	@Insert("insert into admin(admin_name,admin_pwd ,system_admin) values (#{User_name}, #{User_pass},#{system_admin})")
	public int insertRole(@Param("User_name") String User_name, @Param("User_pass") String User_pass,
			@Param("system_admin") int system_admin);

}
