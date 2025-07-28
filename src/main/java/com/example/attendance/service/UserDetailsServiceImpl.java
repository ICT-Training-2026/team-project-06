package com.example.attendance.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
		logger.debug("Attempting to load user: {}", employeeId);
		Account account = loginRepository.findByEmployeeId(employeeId);

		if (account == null) {
			logger.warn("User not found: {}", employeeId);
			throw new UsernameNotFoundException("User " + employeeId + " was not found in the database");
		}

		logger.debug("User found: {}", account.getEmployeeId());
		// ここでパスワードをログに出力するのはセキュリティ上問題があるため、実際の運用では避けてください
		logger.debug("Password: {}", account.getPassword());

		return new User(account.getEmployeeId(), account.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("USER")));
	}
}