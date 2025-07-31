package com.example.attendance.service;

import java.time.LocalDate;

public interface MonthlyTotalService {
	void calculateAndUpdateOvertime(String employeeId, LocalDate date);
}
