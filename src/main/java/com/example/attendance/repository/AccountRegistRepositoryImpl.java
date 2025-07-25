package com.example.attendance.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRegistRepositoryImpl implements AccountRegistRepository {

private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Account acount) {
		String sql = "INSERT INTO application"
			+ "(employee_id, department_id, job_id, name, password)"
			+ "VALUES (?, ?, ?, ?, ?)";
		
			jdbcTemplate.update(sql,
			acount.getEmployeeId(),
			acount.getDepartmentId(),
			acount.getJobId(),
			acount.getName(),
			acount.getPassword()
			);
	}
}