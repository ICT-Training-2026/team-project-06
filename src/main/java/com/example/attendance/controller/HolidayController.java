package com.example.attendance.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.attendance.entity.Prescribed;
import com.example.attendance.service.HolidayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService service;

    /** 休日登録処理 */
    @PostMapping("/holiday-register")
    @ResponseBody
    public ResponseEntity<?> registerMultipleHolidays(@RequestBody Map<String, Integer> holidayData) {
        try {
            int currentYear = LocalDate.now().getYear();
            int nextYear = currentYear + 1;
            for (Map.Entry<String, Integer> entry : holidayData.entrySet()) {
                String monthStr = entry.getKey().replace("月", "");
                int month = Integer.parseInt(monthStr);
                int holidayCount = entry.getValue();
                Prescribed prescribed = new Prescribed();
                prescribed.setMonthlyAttendance(String.valueOf(holidayCount));
                int totalHours = holidayCount * 7;
                String laborStr = String.format("%d:00:00", totalHours);
                prescribed.setPrescribedLabor(laborStr);
                int year = (month >= 4) ? currentYear : nextYear;
                String formattedMonth = String.format("%02d", month);
                Date date = Date.valueOf(year + "-" + formattedMonth + "-01");
                prescribed.setMonth(date);
                // ここを変更: register メソッドの代わりに registerOrUpdate を使用
                service.registerOrUpdate(prescribed);
            }
            return ResponseEntity.ok("{\"status\":\"success\", \"message\":\"出勤日の登録が完了しました。\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    /** 月別休日一覧表示 */
    @GetMapping("/holiday-list")
    public String showHolidayList(@ModelAttribute("month") int month) {
        // 月別の休日一覧を取得するロジックを実装
        return "holiday-list";
    }


}