package com.example.attendance.service;

import java.time.LocalDate;
import java.util.List;

import com.example.attendance.entity.Attendance;

public interface AttendanceService {
    void recordStart(String employeeId, String categoryId);
    void recordBreakStart(String employeeId);
    void recordBreakEnd(String employeeId);
    void recordEnd(String employeeId);
    void approve(String employeeId, LocalDate date);
    Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date);
    List<Attendance> findByEmployeeIdAndPeriod(String employeeId, LocalDate start, LocalDate end);
    List<Attendance> adminSearch(String department, String position, String employeeId, LocalDate startDate, LocalDate endDate);
    void updateAttendance(String employeeId, LocalDate date, String startTime, String closingTime, String workTime, String breakTime);
    void deleteAttendance(String employeeId, LocalDate date);
}