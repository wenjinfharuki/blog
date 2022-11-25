package ren.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ren.example.model.entity.BlogsEntity;
import ren.example.model.service.BlogsService;

@Controller
public class BlogEditController {
	@Autowired
	BlogsService blogsService;

//ブログ一覧
	@GetMapping("/blogdetail/{blogId}")
	public String getBlogDetailPage(@PathVariable Long blogId, Model model) {
		BlogsEntity blogs = blogsService.selectByBlogId(blogId);
		model.addAttribute("blogs", blogs);
		return "blogEdit.html";
	}

//更新
	@PostMapping("/blogupdate")
	public String updateData(@RequestParam Long blogId, @RequestParam String blogTitle, @RequestParam String message) {
		blogsService.update(blogId, blogTitle, message);
		return "redirect:/homepage";
	}

//削除
	@PostMapping("/delete")
	public String blogDelete(@RequestParam Long blogId) {
		blogsService.deleteBlog(blogId);
		return "redirect:/homepage";
	}
}
