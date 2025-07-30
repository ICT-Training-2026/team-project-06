package com.example.attendance.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Application;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(Application application) {
		String sql = "INSERT INTO application" + "(employee_id, category_id, comment, date_apply)"
				+ "VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, application.getEmployeeId(), application.getCategoryId(), application.getComment(),
				application.getDateApply());

	}

	@Override
	public void updatePaidVacation(String employeeId) {
		String sql = "UPDATE account SET paid_vacation = paid_vacation - 1 WHERE employee_id = ?";
		jdbcTemplate.update(sql, employeeId);
	}

	@Override
	public void updateRemainingVacation(String employeeId) {
		String sql = "UPDATE account SET remaining_vacation = remaining_vacation - 1 WHERE employee_id = ?";
        jdbcTemplate.update(sql, employeeId);
	}

}