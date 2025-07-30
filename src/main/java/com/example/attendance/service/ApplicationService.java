package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Application;

@Service
public interface ApplicationService {

	void regist(Application application);

	void updateVacation(String categoryId, String employeeId);
}