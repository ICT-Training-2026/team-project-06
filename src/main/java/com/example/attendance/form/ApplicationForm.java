package com.example.attendance.form;

import java.sql.Date;

import lombok.Data;

@Data
public class ApplicationForm {
	private int employeeId;
	private int categoryId;
	private String comment;
	private Date dateApply;
}
