package com.example.attendance.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
	private String userId;
	private LocalDate date;
	private String categoryId;
	private LocalTime startTime;
	private LocalTime breakStar;
	private LocalTime breakEnd;
	private LocalTime endTime;
	private int workDuration;
	private int breakDuration;
	private int overtime;
	private String workStatusld;
	private boolean checked;
	public int calculateWorkTime() {
		
	};
	public int calculateOvertime() {
		
	};
	public boolean isValid() {
		
	};
}
