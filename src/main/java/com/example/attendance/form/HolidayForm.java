package com.example.attendance.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Data;

@Data
public class HolidayForm {

    @Min(value = 0)
    @Max(value = 99, message = "4月の休暇日数は99以下の値を入力してください。")
    private Integer aprilDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "5月の休暇日数は99以下の値を入力してください。")
    private Integer mayDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "6月の休暇日数は99以下の値を入力してください。")
    private Integer juneDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "7月の休暇日数は99以下の値を入力してください。")
    private Integer julyDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "8月の休暇日数は99以下の値を入力してください。")
    private Integer augustDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "9月の休暇日数は99以下の値を入力してください。")
    private Integer septemberDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "10月の休暇日数は99以下の値を入力してください。")
    private Integer octoberDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "11月の休暇日数は99以下の値を入力してください。")
    private Integer novemberDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "12月の休暇日数は99以下の値を入力してください。")
    private Integer decemberDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "1月の休暇日数は99以下の値を入力してください。")
    private Integer januaryDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "2月の休暇日数は99以下の値を入力してください。")
    private Integer februaryDays = 0;

    @Min(value = 0)
    @Max(value = 99, message = "3月の休暇日数は99以下の値を入力してください。")
    private Integer marchDays = 0;
}