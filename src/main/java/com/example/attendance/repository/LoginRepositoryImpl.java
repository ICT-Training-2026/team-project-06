package com.example.attendance.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Account;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component
@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LoginRepositoryImpl.class);

    @Override
    public Account findByEmployeeId(String employeeId) {
        String sql = "SELECT employee_id, name, department_id, job_id, password, paid_vacation, remaining_vacation " +
                     "FROM account " +
                     "WHERE employee_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Account account = new Account();
                account.setEmployeeId(rs.getString("employee_id"));
                account.setName(rs.getString("name"));
                account.setDepartmentId(rs.getString("department_id"));
                account.setJobId(rs.getString("job_id"));
                account.setPassword(rs.getString("password"));
                account.setPaidVacation(rs.getInt("paid_vacation"));
                account.setRemainingVacation(rs.getInt("remaining_vacation"));
                logger.info(account.getEmployeeId());
                return account;
            }, employeeId);
        } catch (EmptyResultDataAccessException e) {
            // 該当するemployee_idが見つからない場合はnullを返す
        	logger.info("null!!!");
            return null;
        }
    }
}