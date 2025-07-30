package com.example.attendance.form;

import java.sql.Date;

import lombok.Data;

@Data
public class ApplicationForm{
	private String employeeId;
	private String categoryId;
	private Date startDate;
	private Date endDate;
	private String comment;
}