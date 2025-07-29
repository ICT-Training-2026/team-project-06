package com.example.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Account;

@Repository
public interface LoginRepository extends JpaRepository<Account, String> {
    Account findByEmployeeId(String employeeId);
}