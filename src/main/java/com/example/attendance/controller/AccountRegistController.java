//
//package com.example.attendance.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.example.attendance.entity.Account;
//import com.example.attendance.form.AccountRegistForm;
//import com.example.demo.serviceuser registrationService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class AccountRegistController {
//
//    private final registrationService service;
//
//    /** アカウント登録画面表示 */
//    @GetMapping("/userRegistration")
//    public String showAccountRegistForm(@ModelAttribute AccountRegistForm form) {
//        return "userRegistration";
//    }
//
//    /** 入力確認画面へ遷移 */
//    @PostMapping("/account-check")
//    public String checkAccountRegist(
//            @Validated @ModelAttribute AccountRegistForm form,
//            BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "userRegistration";
//        }
//
//        return "userRegistration";
//    }
//
//    /** アカウント登録処理 */
//    @PostMapping("/userRegistrationer")
//    public String registerAccount(
//            @Validated @ModelAttribute AccountRegistForm form,
//            BindingResult result,
//            RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//            return "userRegistration";
//        }
//
//        // フォームの内容をエンティティに変換
//        Account account = new Account();
//        account.setEmployeeId(form.getEmployeeId());
//        account.setName(form.getName());
//        account.setDepartment(form.getDepartment());
//        account.setEmail(form.getEmail());
//
//        // サービス呼び出しでDB登録
//        service.regist(account);
//
//        redirectAttributes.addFlashAttribute("msg", "アカウント登録が完了しました。");
//
//        return "redirect:/account-complete";
//    }
//
//    /** 完了画面表示 */
//    @GetMapping("/account-complete")
//    public String showCompletePage() {
//        return "account-complete";
//    }
//}
