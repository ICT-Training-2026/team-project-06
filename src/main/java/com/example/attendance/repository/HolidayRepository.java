package com.example.attendance.repository;

import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Prescribed;

@Repository
public interface HolidayRepository {
	public void save(Prescribed holiday);
}