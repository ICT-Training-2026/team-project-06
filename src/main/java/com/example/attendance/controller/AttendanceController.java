package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/start")
    public void recordStart(@RequestParam String employeeId,
                            @RequestParam String categoryId) {
        attendanceService.recordStart(employeeId, categoryId);
    }

    @PostMapping("/breakStart")
    public void recordBreakStart(@RequestParam String employeeId) {
        attendanceService.recordBreakStart(employeeId);
    }

    @PostMapping("/breakEnd")
    public void recordBreakEnd(@RequestParam String employeeId) {
        attendanceService.recordBreakEnd(employeeId);
    }

    @PostMapping("/end")
    public void recordEnd(@RequestParam String employeeId) {
        attendanceService.recordEnd(employeeId);
    }
}