package com.example.attendance.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AccountRegistForm {

    @NotBlank(message = "社員IDは必須です。")
    @Size(min = 6, max = 6, message = "社員IDは6文字で指定してください。")
    private String employeeId;

    @NotBlank(message = "氏名は必須です。")
    @Size(min = 1, max = 20, message = "氏名は1文字から20文字で指定してください。")
    private String name;

    @NotBlank(message = "部署を選択してください。")
    private String departmentId;

    @NotBlank(message = "役職を選択してください。")
    private String jobId;

    @NotBlank(message = "パスワードは必須です。")
    @Size(min = 10, max = 30, message = "パスワードは10文字から30文字で指定してください。")
    private String password;

    // 確認用パスワードフィールドを追加する場合
    // @NotBlank(message = "確認用パスワードは必須です。")
    // private String confirmPassword;

    // 部署と役職のオプションリストを保持するためのフィールドを追加することもできます
    // これらはコントローラーで設定し、ビューで使用することができます
    // private List<String> departmentOptions;
    // private List<String> positionOptions;
}