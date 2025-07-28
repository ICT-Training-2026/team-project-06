package com.example.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/dashboard")
    public String dashboard(Model model) {
        // ここでログインユーザーの情報を取得し、モデルに追加
        return "dashboard";
    }
}