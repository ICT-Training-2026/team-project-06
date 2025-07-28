package com.example.attendance.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Data;

@Data
public class HolidayForm {

    @Min(value = 0, message = "4月の出勤日数は0以上の値を入力してください。")
    @Max(value = 30, message = "4月の出勤日数は30以下の値を入力してください。")
    private Integer aprilDays = 0;

    @Min(value = 0, message = "5月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "5月の出勤日数は31以下の値を入力してください。")
    private Integer mayDays = 0;

    @Min(value = 0, message = "6月の出勤日数は0以上の値を入力してください。")
    @Max(value = 30, message = "6月の出勤日数は30以下の値を入力してください。")
    private Integer juneDays = 0;

    @Min(value = 0, message = "7月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "7月の出勤日数は31以下の値を入力してください。")
    private Integer julyDays = 0;

    @Min(value = 0, message = "8月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "8月の出勤日数は31以下の値を入力してください。")
    private Integer augustDays = 0;

    @Min(value = 0, message = "9月の出勤日数は0以上の値を入力してください。")
    @Max(value = 30, message = "9月の出勤日数は30以下の値を入力してください。")
    private Integer septemberDays = 0;

    @Min(value = 0, message = "10月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "10月の出勤日数は31以下の値を入力してください。")
    private Integer octoberDays = 0;

    @Min(value = 0, message = "11月の出勤日数は0以上の値を入力してください。")
    @Max(value = 30, message = "11月の出勤日数は30以下の値を入力してください。")
    private Integer novemberDays = 0;

    @Min(value = 0, message = "12月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "12月の出勤日数は31以下の値を入力してください。")
    private Integer decemberDays = 0;

    @Min(value = 0, message = "1月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "1月の出勤日数は31以下の値を入力してください。")
    private Integer januaryDays = 0;

    @Min(value = 0, message = "2月の出勤日数は0以上の値を入力してください。")
    @Max(value = 28, message = "2月の出勤日数は28以下の値を入力してください。")
    private Integer februaryDays = 0;

    @Min(value = 0, message = "3月の出勤日数は0以上の値を入力してください。")
    @Max(value = 31, message = "3月の出勤日数は31以下の値を入力してください。")
    private Integer marchDays = 0;
}