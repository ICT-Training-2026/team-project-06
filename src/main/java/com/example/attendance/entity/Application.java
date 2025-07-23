package com.example.attendance.entity;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Application {
    private String userId;
    private String categoryId;
    private LocalDate date;
    private String comment;

    public boolean isValid() {
        throw new UnsupportedOperationException();
    }

}
