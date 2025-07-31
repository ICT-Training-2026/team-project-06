package com.example.attendance.entity;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class MonthlyTotal {
	private String employeeId;
	private Time totalWork;
	private Time totalOvertime;
	private String takeVacation;
	private Date month;
	/*不要？
	private int transferLeaveDaysUsed;
	private int maxPaidLeaveDays;
	private int maxTransferLeaveDays;
	*/
//	public int calculateTotalWorkTime(List<Attendance> attendanceList) {
//
//	};
//	public int calculateOvertime(int standardWorkHours) {
//
//	};

}
