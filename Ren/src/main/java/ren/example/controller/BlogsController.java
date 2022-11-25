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

@Controller
public class BlogsController {
	@Autowired
	BlogsService blogsService;

	// ブログ記事の登録画面の表示
	@GetMapping("/blogcreate")
	public String getBlogCreatePage() {
		return "blogCreate.html";
	}

	// 登録内容を保存
	@PostMapping("/blogregister")
	public String register(@RequestParam String blogTitle, @RequestParam String message, Model model) {
		blogsService.insert(blogTitle, message);
		List<BlogsEntity> blogList = blogsService.selectByAll();
		model.addAttribute("blogList", blogList);
		return "homepage.html";
	}

}
