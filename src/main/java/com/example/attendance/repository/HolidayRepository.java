package com.example.attendance.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository {
	public void add(Holiday holiday);
}