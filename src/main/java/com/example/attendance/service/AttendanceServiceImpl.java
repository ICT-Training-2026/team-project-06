package com.example.attendance.service;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendance.entity.Attendance;
import com.example.attendance.repository.AttendanceDAO;
import com.example.attendance.repository.MonthlyTotalDAO;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDAO attendanceDAO;
    
    @Autowired
    private MonthlyTotalService monthlyTotalService;	
    
    @Autowired
    private MonthlyTotalDAO monthlyTotalDAO;

    @Override
    public void recordStart(String employeeId, String categoryId) {
        Attendance today = attendanceDAO.findByEmployeeIdAndDate(employeeId, LocalDate.now());
        if (today != null && today.getStartTime() != null) return;

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setDate(LocalDate.now());
        attendance.setStartTime(LocalTime.now().withNano(0));
        attendance.setCategoryId(categoryId);     
        attendance.setStatusId("0");             
        attendance.setCategoryStatus(false);      

        attendanceDAO.insert(attendance);
        
        if ("1".equals(categoryId)) {
            attendanceDAO.incrementRemainingVacation(employeeId);
        }

    }

    @Override
    public void recordBreakStart(String employeeId) {
        Attendance today = attendanceDAO.findByEmployeeIdAndDate(employeeId, LocalDate.now());
        if (today != null && today.getStartBreakTime() == null && today.getClosingTime() == null) {
            today.setStartBreakTime(LocalTime.now().withNano(0));
            today.setStatusId("2"); // 休憩中
            attendanceDAO.update(today);
        }
    }

    @Override
    public void recordBreakEnd(String employeeId) {
        Attendance today = attendanceDAO.findByEmployeeIdAndDate(employeeId, LocalDate.now());
        if (today != null && today.getStartBreakTime() != null && today.getEndBreakTime() == null && today.getClosingTime() == null) {
            LocalTime endBreakTime = LocalTime.now().withNano(0);
            today.setEndBreakTime(endBreakTime);
            today.setStatusId("0");

            // ⏱️ 計算breaktime
            java.time.Duration breakDuration = java.time.Duration.between(today.getStartBreakTime(), endBreakTime);
            today.setBreakTime(java.sql.Time.valueOf(LocalTime.MIDNIGHT.plus(breakDuration)));

            attendanceDAO.update(today);
        }
    }

    @Override
    public void recordEnd(String employeeId) {
        Attendance today = attendanceDAO.findByEmployeeIdAndDate(employeeId, LocalDate.now());
        if (today != null && today.getClosingTime() == null && today.getStartTime() != null) {
            LocalTime closingTime = LocalTime.now().withNano(0);
            today.setClosingTime(closingTime);
            today.setStatusId("1");

            // ⏱️ 計算worktime（退勤 - 出勤）
            java.time.Duration totalDuration = java.time.Duration.between(today.getStartTime(), closingTime);

            // ⏱️ 休憩時間あるなら、マイナス
            java.time.Duration breakDuration = java.time.Duration.ZERO;
            if (today.getStartBreakTime() != null && today.getEndBreakTime() != null) {
                breakDuration = java.time.Duration.between(today.getStartBreakTime(), today.getEndBreakTime());
            }

            java.time.Duration workDuration = totalDuration.minus(breakDuration);
            today.setWorkTime(java.sql.Time.valueOf(LocalTime.MIDNIGHT.plus(workDuration)));

            attendanceDAO.update(today);
            
         // ⏱️ 加班時間の計算を即時実行（當月）
            LocalDate monthDate = today.getDate().withDayOfMonth(1);
            monthlyTotalService.calculateAndUpdateOvertime(employeeId, monthDate);

        }
    }

    @Override
    public void approve(String employeeId, LocalDate date) {
        Attendance attendance = attendanceDAO.findByEmployeeIdAndDate(employeeId, date);
        if (attendance != null) {
            attendance.setCategoryStatus(true);
            attendanceDAO.update(attendance);
        }
    }
    
    @Override
    public Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return attendanceDAO.findByEmployeeIdAndDate(employeeId, date);
    }
    
    @Override
    public List<Attendance> findByEmployeeIdAndPeriod(String employeeId, LocalDate start, LocalDate end) {
        List<Attendance> records = attendanceDAO.findByEmployeeIdAndPeriod(employeeId, start, end);

        for (Attendance att : records) {
            LocalDate monthStart = att.getDate().withDayOfMonth(1);
            Time overtime = monthlyTotalDAO.getOvertime(employeeId, monthStart);
            att.setOvertime(overtime != null ? overtime.toString() : "");
        }

        return records;
    }

    
    @Override
    public List<Attendance> adminSearch(String department, String position, String employeeId, LocalDate startDate, LocalDate endDate) {
        List<Attendance> result = attendanceDAO.adminSearch(department, position, employeeId, startDate, endDate);

        // 使用 employeeId + 月份（第一天）为 key，只查一次
        Map<String, String> overtimeMap = new HashMap<>();

        for (Attendance record : result) {
            String empId = record.getEmployeeId();
            LocalDate month = record.getDate().withDayOfMonth(1);  // 统一为当月第一天

            String key = empId + "_" + month;

            if (!overtimeMap.containsKey(key)) {
                String overtime = monthlyTotalDAO.findTotalOvertimeByEmployeeAndMonth(empId, month);
                overtimeMap.put(key, overtime != null ? overtime : "");
            }

            record.setOvertime(overtimeMap.get(key));  // 设定值
        }

        return result;
    }



    @Override
    public void updateAttendance(String employeeId, LocalDate date, String startTime, String closingTime, String workTime, String breakTime) {
        Attendance original = attendanceDAO.findByEmployeeIdAndDate(employeeId, date);
        if (original == null) return;

        if (startTime != null && !startTime.isEmpty()) {
            original.setStartTime(LocalTime.parse(startTime));
        }
        if (closingTime != null && !closingTime.isEmpty()) {
            original.setClosingTime(LocalTime.parse(closingTime));
        }
        if (workTime != null && !workTime.isEmpty()) {
            original.setWorkTime(java.sql.Time.valueOf(LocalTime.MIDNIGHT.plusMinutes(Long.parseLong(workTime))));
        }
        if (breakTime != null && !breakTime.isEmpty()) {
            original.setBreakTime(java.sql.Time.valueOf(LocalTime.MIDNIGHT.plusMinutes(Long.parseLong(breakTime))));
        }

        attendanceDAO.update(original);
    }

    @Override
    public void deleteAttendance(String employeeId, LocalDate date) {
        attendanceDAO.delete(employeeId, date);	
    }

}