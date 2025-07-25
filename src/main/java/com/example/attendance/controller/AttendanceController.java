package com.example.attendance.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.entity.Attendance;
import com.example.attendance.service.AttendanceService;
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/start")
    public ResponseEntity<String> recordStart(@RequestParam String employeeId,
                                              @RequestParam String categoryId) {

        LocalDate today = LocalDate.now();

        // 今日の出勤履歴を検索
        Attendance existing = attendanceService.findByEmployeeIdAndDate(employeeId, today);
        if (existing != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("本日はすでに出勤打刻が記録されています。");
        }
        attendanceService.recordStart(employeeId, categoryId);
        return ResponseEntity.ok("出勤記録が正常に登録されました");
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
    
    @PostMapping("/api/search")
    public ResponseEntity<?> searchAttendanceRecords(@RequestBody Map<String, String> request) {
        try {
            String employeeId = "yao001"; // あとはsessionから取得

            LocalDate startDate = LocalDate.parse(request.get("startDate"));
            LocalDate endDate = LocalDate.parse(request.get("endDate"));

            List<Attendance> results = attendanceService.findByEmployeeIdAndPeriod(employeeId, startDate, endDate);

            return ResponseEntity.ok(results); 

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無効な請求");
        }
    }
}