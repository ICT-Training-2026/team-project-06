package com.example.attendance.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Prescribed;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HolidayRepositoryImpl implements HolidayRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(Prescribed holiday) {
        // 既存のメソッドの内容はそのまま
        String sql = "INSERT INTO prescribed"
            + "(monthly_attendance, prescribed_labor, month)"
            + "VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
            holiday.getMonthlyAttendance(),
            holiday.getPrescribedLabor(),  
            holiday.getMonth()
        );
    }

    // 新しく追加するメソッド
    @Override
    public void updateOrInsert(Prescribed holiday) {
        String sql = "INSERT INTO prescribed (monthly_attendance, prescribed_labor, month) " +
                     "VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE " +
                     "monthly_attendance = VALUES(monthly_attendance), " +
                     "prescribed_labor = VALUES(prescribed_labor)";

        jdbcTemplate.update(sql,
            holiday.getMonthlyAttendance(),
            holiday.getPrescribedLabor(),
            holiday.getMonth()
        );
    }
}