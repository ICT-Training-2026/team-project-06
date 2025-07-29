package com.example.attendance.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Prescribed {
    private String monthlyAttendance;
    private String prescribedLabor;
    private Date month;

    // 追加したフィールドに対するgetterとsetterはLombokの@Dataアノテーションで自動生成されます。

    // 既存のフィールド
    private int standardWorkDays;
    private int holidayCount;
    private int standardWorkHours;
}

//package com.example.attendance.entity;
//
//import java.sql.Date;
//import java.sql.Time;
//
//import lombok.Data;
//
//@Data
//public class Prescribed {
//	private String monthlyAttendance;
//	private Time prescribedLabor;
//	private Date month;
//
//	/*不要？
//	private String yearMonth;
//	private int standardWorkDays;
//	private int holidayCount;
//	private int standardWorkHours;
//	*/
//}
