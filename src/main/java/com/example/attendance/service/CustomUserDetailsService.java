package com.example.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.attendance.entity.Account;
import com.example.attendance.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmployeeId(employeeId);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with employeeId: " + employeeId);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getEmployeeId())
                .password(account.getPassword())
                .roles("USER")
                .build();
    }
}