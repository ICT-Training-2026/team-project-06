package com.example.attendance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
	@Id
	@Column(name = "employee_id")
    private String employeeId;
	@Column(name = "name")
    private String name;
	@Column(name = "department_id")
    private String departmentId;
	@Column(name = "job_id")
    private String jobId;
	@Column(name = "password")
    private String password;
	@Column(name = "paid_vacation")
    private int paidVacation;
    private int remainingVacation;
}