package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Prescribed;
import com.example.attendance.repository.HolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayServicelmpl implements HolidayService {

    private final HolidayRepository repository;

    @Override
    public void register(Prescribed holiday) {
        
        repository.add(holiday);
    }

}