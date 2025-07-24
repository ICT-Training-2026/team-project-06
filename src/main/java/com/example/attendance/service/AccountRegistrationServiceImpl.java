package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Account;
import com.example.attendance.repository.AccountRegistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

	private final AccountRegistRepository repository;
	@Override
	public void register(Account account) {
		// TODO 自動生成されたメソッド・スタブ
		repository.add(account);
	}

}
