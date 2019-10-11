package com.app.model;

public class Admin {
	private Integer adminId;

	private String adminName;

	private String adminPwd;

	private int Systemadmin;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName == null ? null : adminName.trim();
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd == null ? null : adminPwd.trim();
	}

	public int getSystemadmin() {
		return Systemadmin;
	}

	public void setSystemadmin(int isAdmin) {
		this.Systemadmin = isAdmin == 0 ? null : isAdmin;
	}

	@Override
	public String toString() {
		return "Admin{" + "name='" + adminName + '\'' + ", id=" + adminId.toString() + ", adminPwd=" + adminPwd
				+ ",Systemadmin =" + Integer.toString(Systemadmin) + '}';
	}

}