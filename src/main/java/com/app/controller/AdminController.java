package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Admin;
import com.app.service.AdminService;
import com.app.util.CheckLogin;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminService adminService;

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String name, @RequestParam("userpass") String pass,
			HttpServletRequest request, ModelAndView mv) {
		if (CheckLogin.check(request)) {
			mv.setViewName("index");
			return mv;
		} else {
			Admin admin = adminService.adminLogin(name, pass);
			if (admin != null) {

				if (admin.getSystemadmin() == 1) {
					request.getSession().setAttribute("admin", "系统管理员");
				} else {
					request.getSession().setAttribute("admin", "图书管理员");
				}
				mv.setViewName("index");
				return mv;
			} else {
				mv.addObject("error", 1);
				mv.setViewName("login");
				return mv;
			}
		}

	}

	@RequestMapping("/")
	public ModelAndView loginPage(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView indexPage(HttpServletRequest request, ModelAndView mv) {
		if (CheckLogin.check(request)) {
			mv.setViewName("index");
		} else {
			mv.setViewName("login");
		}
		return mv;
	}

}
