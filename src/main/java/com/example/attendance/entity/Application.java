package com.example.attendance.entity;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Application {
    private String employeeId;
    private String categoryId;
    private String comment;
    private LocalDate dateApply;

    public boolean isValid() {
        throw new UnsupportedOperationException();
    }

}
