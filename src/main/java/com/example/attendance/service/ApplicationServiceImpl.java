package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Application;
import com.example.attendance.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository repository;

	@Override
	public void regist(Application application) {
		repository.add(application);
	}

	@Override
	public void updateVacation(String categoryId, String employeeId) {
		switch (Integer.parseInt(categoryId)) {
		case 2:
			repository.updateRemainingVacation(employeeId);
			break;
		case 3:
			repository.updatePaidVacation(employeeId);
			break;
		}
	}

}