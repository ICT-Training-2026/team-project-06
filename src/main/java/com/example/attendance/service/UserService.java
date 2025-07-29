package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Account;
import com.example.attendance.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LoginRepository loginRepository;

    public Account findByEmployeeId(String employeeId) {
        return loginRepository.findByEmployeeId(employeeId);
    }
}
