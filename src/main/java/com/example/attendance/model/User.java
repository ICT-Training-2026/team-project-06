package com.example.attendance.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String name;
    private String password;
    private String departmentId;
    private String positionId;
    private int maxTransferLeaveDays;
    private int maxPaidLeaveDays;

    public boolean loginCheck() {
        throw new UnsupportedOperationException();
    }

    public boolean isAdmin() {
        throw new UnsupportedOperationException();
    }


}
