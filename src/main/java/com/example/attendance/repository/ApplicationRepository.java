package com.example.attendance.repository;

import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Application;

@Repository
public interface ApplicationRepository {
	public void add(Application application);

	void updatePaidVacation(String employeeId);
    void updateRemainingVacation(String employeeId);
}