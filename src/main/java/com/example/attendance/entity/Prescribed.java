package com.example.attendance.entity;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class Prescribed {
    private Date date;
    private String description;

    private String employeeId;
    private Time totalWork;
    private Time totalOvertime;
    private Date month;
    private int takeVacation;
	

	/*不要？
	private String yearMonth;
	private int standardWorkDays;
	private int holidayCount;
	private int standardWorkHours;
	*/
}