package com.example.attendance.service;

import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {

	public void register();
	public void edit();
	public List<Attendance> getByUser();
	public List<Attendance> searchByUser();
	public List<Attendance> searchByDepartment();
	public List<Attendance> searchByPosition();
	public void delete();
	public MonthlyWorkSummary getMonthlySummary();

}
