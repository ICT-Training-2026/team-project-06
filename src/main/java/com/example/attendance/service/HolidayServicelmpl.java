package com.example.attendance.service;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Holiday;
import com.example.attendance.repository.HolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository repository;

    @Override
    public void setHoliday(Holiday holiday) {
        // TODO 自動生成されたメソッド・スタブ
        repository.save(holiday);
    }

}