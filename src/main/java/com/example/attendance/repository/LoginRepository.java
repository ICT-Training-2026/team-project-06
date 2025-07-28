package com.example.attendance.repository;

import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Account;

@Repository
public interface LoginRepository {
    Account findByEmployeeId(String employeeId);
}