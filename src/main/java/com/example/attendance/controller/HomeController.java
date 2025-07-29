package com.example.attendance.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.attendance.entity.Account;
import com.example.attendance.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final UserService userService;

    @GetMapping("/managerhome")
    public String managerHome() {
        return "managerhome";
    }

    @GetMapping("/userhome")
    public String userHome(Model model, Principal principal) {
        if (principal != null) {
            String employeeId = principal.getName();
            Account account = userService.findByEmployeeId(employeeId);
            if (account != null) {
                model.addAttribute("userName", account.getName());
            }
        }
        return "userhome";
    }
}
