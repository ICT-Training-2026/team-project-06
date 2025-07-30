package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Application;

@Service
public interface ApplicationService {

	void regist(Application application);

	/*
    public void apply(String userId, String categoryId, String date, String comment) {
        throw new UnsupportedOperationException();
    }
    */

}