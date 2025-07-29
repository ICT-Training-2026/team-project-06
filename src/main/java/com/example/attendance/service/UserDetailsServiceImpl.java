package com.example.attendance.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
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

	private static final List<String> ADMIN_IDS = Arrays.asList("adm001", "adm002");

	@Override
	public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
		Account account = loginRepository.findByEmployeeId(employeeId);
		if (account == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (ADMIN_IDS.contains(employeeId)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return new User(account.getEmployeeId(), account.getPassword(), authorities);
	}
}