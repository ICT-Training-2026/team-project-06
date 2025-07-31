package com.example.attendance.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendance.repository.MonthlyTotalDAO;

@Service
public class MonthlyTotalServiceImpl implements MonthlyTotalService {

    @Autowired
    private MonthlyTotalDAO monthlyTotalDAO;

    @Override
    public void calculateAndUpdateOvertime(String employeeId, LocalDate date) {
        LocalDate monthDate = date.withDayOfMonth(1);
        System.out.println("▶️ [加班计算] 开始: " + employeeId + " / 月: " + monthDate);

        Integer attendanceDays = monthlyTotalDAO.getMonthlyAttendance(monthDate);
        if (attendanceDays == null) {
            System.out.println("❌ 出社日数取得失败");
            return;
        }

        int vacationDays = monthlyTotalDAO.getCompensatoryDays(employeeId, monthDate);
        int prescribedMinutes = (attendanceDays - vacationDays) * 7 * 60;

        int workedMinutes = monthlyTotalDAO.getTotalWorkedMinutes(employeeId, monthDate);
        int overtime = Math.max(0, workedMinutes - prescribedMinutes);

        System.out.println("🔹 出社日数: " + attendanceDays);
        System.out.println("🔹 振休: " + vacationDays);
        System.out.println("🔹 所定劳动时间: " + prescribedMinutes + " 分");
        System.out.println("🔹 实际出勤时间: " + workedMinutes + " 分");
        System.out.println("✅ 计算得残业: " + overtime + " 分");

        monthlyTotalDAO.updateOrInsertMonthlyTotal(employeeId, monthDate, workedMinutes, overtime, vacationDays);
        System.out.println("💾 monthly_total 更新完毕");
    }
}
