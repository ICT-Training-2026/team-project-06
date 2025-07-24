package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Account;
import com.example.demo.form.AccountRegistForm;
import com.example.demo.serviceuser registrationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountRegistController {

    private finaluser registrationService service;

    /** アカウント登録画面表示 */
    @GetMapping("/user registration")
    public String showAccountRegistForm(@ModelAttribute AccountRegistForm form) {
        return "user registration";
    }

    /** 入力確認画面へ遷移 */
    @PostMapping("/account-check")
    public String checkAccountRegist(
            @Validated @ModelAttributeuser registrationForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "user registration";
        }

        return "user registration";
    }

    /** アカウント登録処理 */
    @PostMapping("/user registrationer")
    public String registerAccount(
            @Validated @ModelAttributeuser registrationForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "user registration";
        }

        // フォームの内容をエンティティに変換
        Account account = new Account();
        account.setEmployeeId(form.getEmployeeId());
        account.setName(form.getName());
        account.setDepartment(form.getDepartment());
        account.setEmail(form.getEmail());

        // サービス呼び出しでDB登録
        service.regist(account);

        redirectAttributes.addFlashAttribute("msg", "アカウント登録が完了しました。");

        return "redirect:/account-complete";
    }

    /** 完了画面表示 */
    @GetMapping("/account-complete")
    public String showCompletePage() {
        return "account-complete";
    }
}
