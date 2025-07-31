package com.example.attendance.repository;

import java.sql.Time;
import java.time.LocalDate;

public interface MonthlyTotalDAO {

    // ① 获取出社日数（prescribed）
    Integer getMonthlyAttendance(LocalDate monthDate);

    // ② 获取振休日数（application）
    int getCompensatoryDays(String employeeId, LocalDate monthDate);

    // ③ 获取出勤总时间（attendance）
    int getTotalWorkedMinutes(String employeeId, LocalDate monthDate);

    // ④ 更新或插入 monthly_total 表
    void updateOrInsertMonthlyTotal(String employeeId, LocalDate monthDate, int totalWorkMinutes, int overtimeMinutes, int takeVacationDays);
    
    Time getOvertime(String employeeId, LocalDate monthDate);
    
    String findTotalOvertimeByEmployeeAndMonth(String employeeId, LocalDate month);

}
