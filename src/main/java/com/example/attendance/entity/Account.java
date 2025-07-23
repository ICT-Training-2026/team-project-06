package com.example.attendance.entity;

import lombok.Data;

@Data
public class Account {
    private String employeeId;
    private String name;
    private String departmentId;
    private String jobId;
    private String password;
    private int paidVacation;
    private int remainingVacation;

    public boolean loginCheck() {
        throw new UnsupportedOperationException();
    }

    public boolean isAdmin() {
        throw new UnsupportedOperationException();
    }


}
