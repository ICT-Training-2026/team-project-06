package com.example.attendance.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/managerhome")
	public String managerHome() {
		return "managerhome";
	}

	@GetMapping("/userhome")
    public String userHome(Model model, HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        String name = (String) session.getAttribute("Name");

        if (employeeId != null && name != null) {
            model.addAttribute("employeeId", employeeId);
            model.addAttribute("userName", name);
        }
        return "userhome";
    }
}
