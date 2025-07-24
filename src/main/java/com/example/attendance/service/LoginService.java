package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Account;

@Service
public interface LoginService {
    void save(Account account);
    Account findByAccountname(String accountname);
}