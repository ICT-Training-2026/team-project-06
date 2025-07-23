package com.example.attendance.entity;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class Prescribed {
	private String monthlyHolidays;
	private String monthlyAttendance;
	private Time prescribedLabor;
	private Date month;

	/*不要？
	private String yearMonth;
	private int standardWorkDays;
	private int holidayCount;
	private int standardWorkHours;
	*/
}
