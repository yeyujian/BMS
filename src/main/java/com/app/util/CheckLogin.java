package com.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLogin {

	public static boolean check(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {

			return true;
		} else {
			return false;
		}

	}

	public static boolean checkCustom(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {

			return true;
		} else {
			return false;
		}

	}

	public static boolean checkRole(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin").equals("系统管理员")) {
			return true;
		} else {
			return false;
		}

	}

}
