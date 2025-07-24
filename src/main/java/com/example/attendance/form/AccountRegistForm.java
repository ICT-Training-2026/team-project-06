package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AccountRegistForm {

    @NotBlank(message = "社員IDは必須です。")
    @Size(min = 1, max = 10, message = "社員IDは1文字から10文字で指定してください。")
    private String employeeId;

    @NotBlank(message = "氏名は必須です。")
    @Size(min = 1, max = 50, message = "氏名は1文字から50文字で指定してください。")
    private String fullName;

    @NotBlank(message = "部署を選択してください。")
    private String department;

    @NotBlank(message = "役職を選択してください。")
    private String position;

    // 部署と役職のオプションリストを保持するためのフィールドを追加することもできます
    // これらはコントローラーで設定し、ビューで使用することができます
    // private List<String> departmentOptions;
    // private List<String> positionOptions;
}