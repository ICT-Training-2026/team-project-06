package com.example.attendance.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.attendance.entity.Account;
import com.example.attendance.form.AccountRegistForm;
import com.example.attendance.service.AccountRegistrationService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class AccountRegistController {
    private final AccountRegistrationService service;
    /** アカウント登録画面表示 */
    @GetMapping("/userRegistration")
    public String showAccountRegistForm(@ModelAttribute AccountRegistForm form) {
        return "userRegistration";
    }
    /** 入力確認画面へ遷移 */
    @PostMapping("/account-check")
    public String checkAccountRegist(
            @Validated @ModelAttribute AccountRegistForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "userRegistration";
        }
        return "userRegistration";
    }
    /** アカウント登録処理 */
    @PostMapping("/userRegistrationer")
    @ResponseBody
    public ResponseEntity<String> registerAccount(@RequestBody AccountRegistForm form) {
        // :チェックマーク_緑: ステップ1: メソッドが呼び出されたか確認
        System.out.println(":チェックマーク_緑: registerAccount メソッドが呼び出されました");
        System.out.println("form = " + form);
        // :チェックマーク_緑: ステップ2: 各フィールドの値を出力（nullになっていないか確認）
        System.out.println("employeeId = " + form.getEmployeeId());
        System.out.println("departmentId = " + form.getDepartmentId());
        System.out.println("jobId = " + form.getJobId());
        System.out.println("name = " + form.getName());
        System.out.println("password = " + form.getPassword());
        try {
            // :チェックマーク_緑: ステップ3: Accountオブジェクトに値をセット
            Account account = new Account();
            account.setEmployeeId(form.getEmployeeId());
            account.setDepartmentId(form.getDepartmentId());
            account.setJobId(form.getJobId());
            account.setName(form.getName());
            account.setPassword(form.getPassword());
            account.setPaidVacation(20);
            account.setRemainingVacation(0);
            // :チェックマーク_緑: ステップ4: 挿入予定の内容を表示（念のため）
            System.out.println("挿入対象のアカウント情報: " + account);
            // :チェックマーク_緑: ステップ5: DBに登録
            service.regist(account);
            System.out.println(":チェックマーク_緑: データベースへの登録に成功しました");
            return ResponseEntity.ok("登録成功");
        } catch (Exception e) {
            // :x: 例外が発生した場合のログ出力
            System.out.println(":x: データベースへの登録に失敗しました");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("登録に失敗しました");
        }
    }
    /** 完了画面表示 */
    @GetMapping("/account-complete")
    public String showCompletePage() {
        return "account-complete";
    }
}
