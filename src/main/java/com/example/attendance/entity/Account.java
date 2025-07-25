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
    @Column(name = "employee_id", length = 6)
    private String employeeId;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "department_id", length = 4, nullable = false)
    private String departmentId;

    @Column(name = "job_id", length = 2, nullable = false)
    private String jobId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "paid_vacation", nullable = false)
    private int paidVacation;

    @Column(name = "remaining_vacation", nullable = false)
    private int remainingVacation;

    // Getters and setters
}
