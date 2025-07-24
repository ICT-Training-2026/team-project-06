package com.example.attendance.service;

import java.time.LocalDate;

public interface AttendanceService {
    void recordStart(String employeeId, String categoryId);
    void recordBreakStart(String employeeId);
    void recordBreakEnd(String employeeId);
    void recordEnd(String employeeId);
    void approve(String employeeId, LocalDate date);
}