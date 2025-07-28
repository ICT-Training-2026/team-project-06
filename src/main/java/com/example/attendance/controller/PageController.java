package com.example.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/attendance/register")
    public String showAttendancePage() {
        return "AttendanceRegistration"; // templates/AttendanceRegistration.html
    }
    
    @GetMapping("/attendance/clockin-page")
    public String showClockInPage() {
        return "ClockIn"; // templates/ClockIn.html
    }

    @GetMapping("/attendance/clockout-page")
    public String showClockOutPage() {
        return "ClockOut"; // templates/ClockOut.html
    }

    @GetMapping("/attendance/breakstart-page")
    public String showBreakStartPage() {
        return "StartBreak"; //  StartBreak.html
    }

    @GetMapping("/attendance/breakend-page")
    public String showBreakEndPage() {
        return "ENDBreak"; //  templates/ENDBreak.html
    }
    
    // ホーム画面
    @GetMapping("/userhome")
    public String showUserHome() {
        return "userhome"; // resources/templates/userhome.html
    }
    
    // ログイン画面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // resources/templates/login.html
    }
    
    // 勤怠確認画面
    @GetMapping("/attendance/confirmation")
    public String showAttendanceConfirmationPage() {
        return "AttendanceConfirmation"; 
    }
    
    // 勤怠管理画面
    @GetMapping("/attendance/management")
    public String showAttendanceManagementPage() {
        return "AttendanceManagement"; 
    }
    
    // 勤怠管理画面
    @GetMapping("/attendance/edit")
    public String showAttendanceEditPage() {
        return "AttendanceEdit"; 
    }
}
