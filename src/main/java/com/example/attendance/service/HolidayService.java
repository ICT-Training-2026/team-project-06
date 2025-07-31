package com.example.attendance.service;

import com.example.attendance.entity.Prescribed;

public interface HolidayService {
	
	void register(Prescribed holiday);
	void registerOrUpdate(Prescribed holiday);

}
