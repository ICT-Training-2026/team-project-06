package com.example.attendance.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Attendance;

@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final String SELECT_PERIOD_SQL =
    	    "SELECT * FROM attendance WHERE employee_id = ? AND date BETWEEN ? AND ?";

    private static final String SELECT_SQL = "SELECT * FROM attendance WHERE employee_id = ? AND date = ?";

    private static final String INSERT_SQL =
            "INSERT INTO attendance (employee_id, date, start_time, category_id, status_id, category_status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
    	    "UPDATE attendance SET start_time = ?, closing_time = ?, startbreak_time = ?, endbreak_time = ?, " +
    	    "worktime = ?, breaktime = ?, status_id = ?, category_status = ? WHERE employee_id = ? AND date = ?";

    
    @Override
    public List<Attendance> findByEmployeeIdAndPeriod(String employeeId, LocalDate start, LocalDate end) {
        return jdbcTemplate.query(SELECT_PERIOD_SQL, (rs, rowNum) -> mapRow(rs), employeeId, start, end);
    }
    
    @Override
    public Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date) {
        List<Attendance> results = jdbcTemplate.query(SELECT_SQL, (rs, rowNum) -> mapRow(rs), employeeId, date);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public void insert(Attendance attendance) {
        jdbcTemplate.update(INSERT_SQL,
                attendance.getEmployeeId(),
                attendance.getDate(),
                attendance.getStartTime() != null ? Timestamp.valueOf(LocalDateTime.of(attendance.getDate(), attendance.getStartTime())) : null,
                attendance.getCategoryId(),
                attendance.getStatusId(),
                attendance.isCategoryStatus());
    }

    @Override
    public void update(Attendance attendance) {
        jdbcTemplate.update(UPDATE_SQL,
            attendance.getStartTime() != null ? Timestamp.valueOf(LocalDateTime.of(attendance.getDate(), attendance.getStartTime())) : null,
            attendance.getClosingTime() != null ? Timestamp.valueOf(LocalDateTime.of(attendance.getDate(), attendance.getClosingTime())) : null,
            attendance.getStartBreakTime() != null ? Timestamp.valueOf(LocalDateTime.of(attendance.getDate(), attendance.getStartBreakTime())) : null,
            attendance.getEndBreakTime() != null ? Timestamp.valueOf(LocalDateTime.of(attendance.getDate(), attendance.getEndBreakTime())) : null,
            attendance.getWorkTime(),  // java.sql.Time
            attendance.getBreakTime(), // java.sql.Time
            attendance.getStatusId(),
            attendance.isCategoryStatus(),
            attendance.getEmployeeId(),
            attendance.getDate()
        );
    }
    private Attendance mapRow(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(rs.getString("employee_id"));
        attendance.setDate(rs.getDate("date").toLocalDate());
        attendance.setStartTime(rs.getTimestamp("start_time") != null ? rs.getTimestamp("start_time").toLocalDateTime().toLocalTime() : null);
        attendance.setClosingTime(rs.getTimestamp("closing_time") != null ? rs.getTimestamp("closing_time").toLocalDateTime().toLocalTime() : null);
        attendance.setStartBreakTime(rs.getTimestamp("startbreak_time") != null ? rs.getTimestamp("startbreak_time").toLocalDateTime().toLocalTime() : null);
        attendance.setEndBreakTime(rs.getTimestamp("endbreak_time") != null ? rs.getTimestamp("endbreak_time").toLocalDateTime().toLocalTime() : null);
        attendance.setWorkTime(rs.getTime("worktime"));
        attendance.setBreakTime(rs.getTime("breaktime"));
        attendance.setCategoryId(rs.getString("category_id"));
        attendance.setStatusId(rs.getString("status_id"));
        attendance.setCategoryStatus(rs.getBoolean("category_status"));
        return attendance;
    }
}