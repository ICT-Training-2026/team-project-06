package com.example.attendance.entity;
import java.sql.Date;

import lombok.Data;

@Data
public class Application {
    private String employeeId;
    private String categoryId;
    private String comment;
    private Date dateApply;

    public boolean isValid() {
        throw new UnsupportedOperationException();
    }

}
