package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.BookDao;
import com.app.model.Book;
import com.app.model.User;
import com.app.model.view.BorrowingBooksView;
import com.app.service.AdminUserService;
import com.app.service.BorrowingBooksService;
import com.app.service.UserService;
import com.app.util.CheckLogin;

@RestController
@RequestMapping("/user/usercontroller")
public class Userusercontroller {

	@Resource
	private UserService userService;
	@Resource
	private BorrowingBooksService borrowingBooksService;
	@Resource
	private AdminUserService adminUserService;

	@Autowired
	private BookDao bookDao;

	@RequestMapping("/")
	public ModelAndView userUserControllerPage(HttpServletRequest request, ModelAndView mv) {
		if (!CheckLogin.checkCustom(request)) {
			mv.setViewName("loginuser");
		} else {
			mv.setViewName("userusercontroller");
		}

		return mv;
	}

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String name, @RequestParam("userpass") String pass,
			HttpServletRequest request, ModelAndView mv) // 使用ModelAndView类用来存储处理完后的结果数据，以及显示该数据的视图
	{

		User user = adminUserService.adminUserLogin(name, pass);// 进入dao寻找是否存在该用户

		if (user != null) {
//				

			request.getSession().setAttribute("user", "用户");// 在网络得到这个会话并对user实体赋值
			request.getSession().setAttribute("Id", user.getUserId().toString());
			mv.setViewName("userindex");// 为mv设置视图名

		} else {
			mv.setViewName("loginuser");// 为mv设置视图名
			mv.addObject("error", 1);// 在login.html的第100行
		}

		return mv;
	}

	@RequestMapping("/getuserinfo")
	public Map<String, Object> getUserUserInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!CheckLogin.checkCustom(request)) {
			map.put("error", "true");
			map.put("msg", "请登录");
			return map;
		}
		int id = Integer.parseInt((String) request.getSession().getAttribute("Id"));
		User user = userService.findUserById(id);
		if (user == null) {
			map.put("error", "true");
			map.put("msg", "未找到用户");
			return map;
		}
		List<BorrowingBooksView> res = userService.userSelectByPageNum(id);
		if (res == null) {
			map.put("error", "false");
			map.put("msg", "");
			map.put("user", user);
			return map;
		}
		map.put("error", "false");
		map.put("msg", "");
		map.put("user", user);
		map.put("data", res);
		return map;

	}

	@RequestMapping("/change")
	public Map<String, Object> changeUser(User userInfo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!CheckLogin.checkCustom(request)) {
			map.put("error", "true");
			map.put("msg", "未登录！");
			return map;
		} else {
			int id = Integer.parseInt((String) request.getSession().getAttribute("Id"));
			userInfo.setUserId(id);
			if (!userService.changeUser(userInfo)) {
				map.put("error", "true");
				map.put("msg", "修改失败");
			} else {
				map.put("error", "false");
				map.put("msg", "修改成功");
			}
			return map;
		}
	}

	@RequestMapping("/selcetbook")
	public Map<String, Object> selectBook(@RequestParam("Book_name") String name, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!CheckLogin.checkCustom(request)) {
			map.put("error", "true");
			map.put("msg", "未登录！");
			return map;
		} else {
			List<Book> bookList = bookDao.selectByName(name);
			if (bookList != null) {
				map.put("error", "false");
				map.put("msg", "");
				map.put("booklist", bookList);
			} else {
				map.put("error", "true");
				map.put("msg", "无");
			}
			return map;
		}
	}

}
