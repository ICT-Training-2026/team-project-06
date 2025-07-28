package com.example.attendance.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Attendance {
    private String employeeId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime closingTime;
    private LocalTime startBreakTime;
    private LocalTime endBreakTime;
    private Time workTime;
    private Time breakTime;
    private String categoryId;
    private String statusId;
    private boolean categoryStatus;
    private String name;
    private String departmentId;
    private String jobId;
}