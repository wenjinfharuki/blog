package ren.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ren.example.model.entity.BlogsEntity;
import ren.example.model.service.BlogsService;
import ren.example.model.service.UserService;

@Controller
public class LoginController {
	@Autowired
	BlogsService blogsService;
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}

	@GetMapping("/homepage")
	public String getHomepage(Model model) {
		List<BlogsEntity> blogList = blogsService.selectByAll();
		model.addAttribute("blogList", blogList);
		return "homepage.html";
	}


	// @PostMapping("/login")
	// public String enterHomepage(@RequestParam String username,@RequestParam
	// String password,Model model) {
//		List<UserEntity> userList=userService.getAllAccounts();
//		for(UserEntity user:userList) {
//			System.out.println(user);
//		}
//		if(!user.getUsername.equals(username)||uesr.getPassword.equals(password)) {
//			return "";
//		}

	// model.addAttribute("username", username);
	// return "homepage.html";
	// }
}
