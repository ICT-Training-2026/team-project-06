package com.example.attendance.repository;

import java.time.LocalDate;

import com.example.attendance.model.Attendance;

public class AttendanceDAO {
	public void insert(Attendance att) {
	
	};
	
	public void update(Attendance att){
	
	};
	public List<Attendance> findByUser(String userId){
	
	};
	public List<Attendance> findByDateRange(String userId, LocalDate from,LocalDate to){
	
	};
	
	public  List<Attendance> findByDepartmentId(String departmentId){
	
	};
	public List<Attendance> findByPositaionId(String positionId){
	
	};
	public void delete(String userId,LocalDate date){
	
	}; 
}
