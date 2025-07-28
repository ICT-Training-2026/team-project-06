package com.example.attendance.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HolidayRepositoryImpl implements HolidayRepository {
	
private final JdbcTemplate jdbcTemplate;

@Override
public void add(Holiday holiday) {
	String sql = "INSERT INTO holiday"
        + "(employee_id, total_work, total_overtime, take_vacation, month)"
        + "VALUES (?, ?, ?, ?, ?)";
	
	
		jdbcTemplate.update(sql,
            holiday.getEmployeeId(),
            holiday.getTotalWork(),
            holiday.getTotalOvertime(),
            holiday.getTakeVacation(),
            holiday.getMonth()
		);
	}
}