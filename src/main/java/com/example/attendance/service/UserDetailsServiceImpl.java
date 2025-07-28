package com.example.attendance.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.attendance.entity.Account;
import com.example.attendance.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
        Account account = loginRepository.findByEmployeeId(employeeId);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(account.getEmployeeId(), account.getPassword(), new ArrayList<>());
    }
}