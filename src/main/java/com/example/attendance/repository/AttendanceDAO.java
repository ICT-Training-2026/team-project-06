package com.example.attendance.repository;

import java.time.LocalDate;

import com.example.attendance.entity.Attendance;

public interface AttendanceDAO {
    Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date);
    void insert(Attendance attendance);
    void update(Attendance attendance);
}