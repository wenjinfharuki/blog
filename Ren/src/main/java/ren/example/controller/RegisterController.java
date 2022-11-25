package ren.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ren.example.model.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;

	// 新規登録画面を表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}

	// 新規登録画面を表示
	// @GetMapping("/newAccount")
	// public String getNewAccountPage(Model model) {
	// return "newAccount.html";
	// }

	// ユーザー情報の登録
	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password, Model model) {
		// もし保存をした場合には、newAccount.htmlへ遷移する
		if (userService.createAccount(username, password)) {
			model.addAttribute("username", username);
			return "newAccount.html";
		} else {// そうでない場合には、register.htmlに遷移する
			model.addAttribute("error", true);
			return "register.html";
		} 
	}

}
