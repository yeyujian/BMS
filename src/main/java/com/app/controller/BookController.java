package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Book;
import com.app.service.AdminService;
import com.app.service.BookService;
import com.app.util.CheckLogin;

@RestController
@RequestMapping("/admin/bookcontroller")
public class BookController {

	@Resource
	private AdminService adminService;
	@Resource
	private BookService bookService;

	@RequestMapping("/")
	public ModelAndView bookControllerPage(HttpServletRequest request, ModelAndView mv) {
		if (CheckLogin.check(request)) {
			mv.setViewName("/bookcontroller");
			mv.addObject("classNum", 1);
		} else {
			mv.setViewName("/login");
			mv.addObject("error", 1);

		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView addBook(@RequestParam("Book_name") String name, @RequestParam("Book_category") String category,
			@RequestParam("Book_auth") String auth, @RequestParam("Book_publish") String publish,
			@RequestParam("Book_price") String Bprice, @RequestParam("Book_introduction") String introduction,
			HttpServletRequest request, ModelAndView mv) {
		double price = Double.valueOf(Bprice);
		if (!CheckLogin.check(request)) {
			mv.setViewName("/login");
			return mv;
		}
		mv.setViewName("/bookcontroller");
		Book book = new Book();
		book.setBookAuthor(auth);
		book.setBookCategory(category);
		book.setBookIntroduction(introduction);
		book.setBookName(name);
		book.setBookPrice(price);
		book.setBookPublish(publish);
		if (!bookService.addBook(book)) {
			mv.addObject("error1", 1);
		}
		mv.addObject("classNum", 1);
		return mv;
	}

	@RequestMapping("/change")
	public ModelAndView changeBook(@RequestParam("Book_id") int id, @RequestParam("Book_name") String name,
			@RequestParam("Book_category") String category, @RequestParam("Book_auth") String auth,
			@RequestParam("Book_publish") String publish, @RequestParam("Book_price") String Bprice,
			@RequestParam("Book_introduction") String introduction, HttpServletRequest request, ModelAndView mv) {
		double price = Double.valueOf(Bprice);
		if (!CheckLogin.check(request)) {
			mv.setViewName("/login");
			return mv;
		}
		mv.setViewName("/bookcontroller");
		Book book = new Book();
		book.setBookId(id);
		book.setBookAuthor(auth);
		book.setBookCategory(category);
		book.setBookIntroduction(introduction);
		book.setBookName(name);
		book.setBookPrice(price);
		book.setBookPublish(publish);
		if (!bookService.changeBook(book)) {
			mv.addObject("error2", 1);
		}
		mv.addObject("classNum", 2);
		return mv;
	}

	@RequestMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("Book_id") int Book_id, HttpServletRequest request, ModelAndView mv) {
		if (!CheckLogin.check(request)) {
			mv.setViewName("/login");
			return mv;
		}
		mv.setViewName("/bookcontroller");
		if (!bookService.delectBookById(Book_id)) {
			mv.addObject("error5", 1);
		}
		mv.addObject("classNum", 5);
		return mv;
	}
}
