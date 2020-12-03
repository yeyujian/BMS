package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.service.AdminService;
import com.app.service.BorrowingBooksService;
import com.app.util.CheckLogin;

@RestController
@RequestMapping("/admin/borrowingcontroller")
public class BorrowingController {
	@Resource
	private AdminService adminService;
	@Resource
	private BorrowingBooksService borrowingBooksService;

	@RequestMapping("/borrow")
	public ModelAndView borrowBook(@RequestParam("Book_id") int Bookid, @RequestParam("User_id") int Userid,
			HttpServletRequest request, ModelAndView mv) {
		if (!CheckLogin.check(request)) {
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("bookcontroller");
		if (!borrowingBooksService.borrowBook(Bookid, Userid)) {
			mv.addObject("error3", 1);
		}
		mv.addObject("classNum", 3);
		return mv;
	}

	@RequestMapping("/return")
	public ModelAndView returnBook(@RequestParam("Book_id") int Bookid, HttpServletRequest request, ModelAndView mv) {
		if (!CheckLogin.check(request)) {
			mv.setViewName("login");
			return mv;
		}
		// System.out.printf("%d -------\n", Bookid);
		mv.setViewName("bookcontroller");
		if (!borrowingBooksService.returnBook(Bookid)) {
			mv.addObject("error4", 1);
		}
		mv.addObject("classNum", 4);
		return mv;
	}
}
