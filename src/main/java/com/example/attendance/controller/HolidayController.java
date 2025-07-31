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
            // 【変更点①】現在の年と次の年を取得（4〜12月は今年、1〜3月は翌年に登録）
            int currentYear = LocalDate.now().getYear();
            int nextYear = currentYear + 1;
            for (Map.Entry<String, Integer> entry : holidayData.entrySet()) {
                // 月の文字列から「月」を除去し、整数に変換（例：「4月」→4）
                String monthStr = entry.getKey().replace("月", "");
                int month = Integer.parseInt(monthStr);
                int holidayCount = entry.getValue();
                // Prescribed エンティティ作成
                Prescribed prescribed = new Prescribed();
                // 出勤日数を文字列として設定（例：20）
                prescribed.setMonthlyAttendance(String.valueOf(holidayCount));
                // 【変更点②】労働時間を1日7時間として計算（例：20日 × 7 = 140時間）
                int totalHours = holidayCount * 7;
                String laborStr = String.format("%d:00:00", totalHours);
                prescribed.setPrescribedLabor(laborStr);
                // 【変更点③】対象の年を設定（4〜12月は今年、1〜3月は翌年）
                int year = (month >= 4) ? currentYear : nextYear;
                // 【変更点④】"yyyy-MM-01" の形式で日付を生成（例：2025-04-01）
                String formattedMonth = String.format("%02d", month);
                Date date = Date.valueOf(year + "-" + formattedMonth + "-01");
                prescribed.setMonth(date);
                // サービスを通してデータベースへ保存
                service.register(prescribed);
            }
            // 正常時のレスポンス返却
            return ResponseEntity.ok("{\"status\":\"success\", \"message\":\"出勤日の登録が完了しました。\"}");
        } catch (Exception e) {
            // エラー発生時のレスポンス返却
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