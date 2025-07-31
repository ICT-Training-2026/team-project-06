package com.example.attendance.repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MonthlyTotalDAOImpl implements MonthlyTotalDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getMonthlyAttendance(LocalDate monthDate) {
        String sql = "SELECT monthly_attendance FROM prescribed WHERE month = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, Date.valueOf(monthDate));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getCompensatoryDays(String employeeId, LocalDate monthDate) {
        String sql = "SELECT COUNT(*) FROM application WHERE employee_id = ? AND category_id = '2' AND DATE_FORMAT(date_apply, '%Y-%m') = ?";
        String ym = monthDate.toString().substring(0, 7); // "2025-07"
        return jdbcTemplate.queryForObject(sql, Integer.class, employeeId, ym);
    }

    @Override
    public int getTotalWorkedMinutes(String employeeId, LocalDate monthDate) {
        String sql = "SELECT TIME_TO_SEC(worktime) / 60 AS minutes " +
                     "FROM attendance " +
                     "WHERE employee_id = ? AND DATE_FORMAT(date, '%Y-%m') = ?";
        String ym = monthDate.toString().substring(0, 7);
        return jdbcTemplate.query(sql, new Object[]{employeeId, ym}, (rs) -> {
            int total = 0;
            while (rs.next()) {
                total += rs.getInt("minutes");
            }
            return total;
        });
    }

    @Override
    public void updateOrInsertMonthlyTotal(String employeeId, LocalDate monthDate, int totalWorkMinutes, int overtimeMinutes, int takeVacationDays) {
        String checkSql = "SELECT COUNT(*) FROM monthly_total WHERE employee_id = ? AND month = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, employeeId, Date.valueOf(monthDate));

        if (count > 0) {
            String updateSql = "UPDATE monthly_total SET total_work = SEC_TO_TIME(? * 60), total_overtime = SEC_TO_TIME(? * 60), take_vacation = ? WHERE employee_id = ? AND month = ?";
            jdbcTemplate.update(updateSql, totalWorkMinutes, overtimeMinutes, String.valueOf(takeVacationDays), employeeId, Date.valueOf(monthDate));
        } else {
            String insertSql = "INSERT INTO monthly_total (employee_id, month, total_work, total_overtime, take_vacation) VALUES (?, ?, SEC_TO_TIME(? * 60), SEC_TO_TIME(? * 60), ?)";
            jdbcTemplate.update(insertSql, employeeId, Date.valueOf(monthDate), totalWorkMinutes, overtimeMinutes, String.valueOf(takeVacationDays));
        }
    }
    
    @Override
    public Time getOvertime(String employeeId, LocalDate monthDate) {
        String sql = "SELECT total_overtime FROM monthly_total WHERE employee_id = ? AND month = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Time.class, employeeId, Date.valueOf(monthDate));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public String findTotalOvertimeByEmployeeAndMonth(String employeeId, LocalDate month) {
        String sql = "SELECT total_overtime FROM monthly_total WHERE employee_id = ? AND month = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, employeeId, month);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
