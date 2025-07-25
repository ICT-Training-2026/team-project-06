package com.example.attendance.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.attendance.entity.Attendance;


public interface AttendanceDAO {
    Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date);
    List<Attendance> findByEmployeeIdAndPeriod(String employeeId, LocalDate start, LocalDate end);
    void insert(Attendance attendance);
    void update(Attendance attendance);
}