package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.User;
import com.app.model.view.BorrowingBooksView;
import com.app.service.BorrowingBooksService;
import com.app.service.UserService;
import com.app.util.CheckLogin;

@RestController
@RequestMapping("/admin/usercontroller")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private BorrowingBooksService borrowingBooksService;

	@RequestMapping("/")
	public ModelAndView userControllerPage(HttpServletRequest request, ModelAndView mv) {
		if (CheckLogin.check(request) && CheckLogin.checkRole(request)) {
			mv.setViewName("/usercontroller");
		} else {
			mv.setViewName("/login");
			mv.addObject("error", 2);
		}
		return mv;
	}

	@RequestMapping("/getuserinfo")
	public Map<String, Object> getUserInfo(@RequestParam("User_id") int id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!(CheckLogin.check(request) && CheckLogin.checkRole(request))) {
			map.put("error", "true");
			map.put("msg", "未登录！");
			return map;
		} else {
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

	}

	@RequestMapping("/delete")
	public Map<String, Object> deleteUser(@RequestParam("User_id") int id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!(CheckLogin.check(request) && CheckLogin.checkRole(request))) {
			map.put("error", "true");
			map.put("msg", "未登录！");
			return map;
		} else {
			if (!userService.deleteUserById(id)) {
				map.put("error", "true");
				map.put("msg", "删除失败");
			} else {
				map.put("error", "false");
				map.put("msg", "删除成功");
			}

			return map;
		}
	}

	@RequestMapping("/add")
	public Map<String, Object> addUser(User userInfo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!(CheckLogin.check(request) && CheckLogin.checkRole(request))) {
			map.put("error", "true");
			map.put("msg", "未登录！");
			return map;
		} else {
			if (!userService.insertUser(userInfo)) {
				map.put("error", "true");
				map.put("msg", "添加失败");
			} else {
				map.put("error", "false");
				map.put("msg", "添加成功");
			}
			return map;
		}
	}

	@RequestMapping("/change")
	public Map<String, Object> changeUser(User userInfo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (!(CheckLogin.check(request) && CheckLogin.checkRole(request))) {
			map.put("error", "true");
			map.put("msg", "未登录！");
			return map;
		} else {
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
}
