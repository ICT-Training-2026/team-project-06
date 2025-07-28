package com.example.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.attendance.entity.Prescribed;
import com.example.attendance.form.HolidayForm;
import com.example.attendance.service.HolidayRegistrationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayRegistrationService service;

    /** 休日登録画面表示 */
    @GetMapping("/holidayRegistration")
    public String showHolidayRegistrationForm(@ModelAttribute HolidayForm form) {
        return "holidayRegistration";
    }

    /** 休日登録処理 */
    @PostMapping("/holidayRegistration")
    public String registerHoliday(
            @Validated @ModelAttribute HolidayForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "holidayRegistration";
        }

        Prescribed prescribed = new Prescribed();
        prescribed.setDate(form.getDate());
        prescribed.setDescription(form.getDescription());
        service.register(prescribed);

        redirectAttributes.addFlashAttribute("msg", "休日登録が完了しました。");
        return "redirect:/holiday-complete";
    }

    /** 完了画面表示 */
    @GetMapping("/holiday-complete")
    public String showCompletePage() {
        return "holiday-complete";
    }

    /** 月別休日一覧表示 */
    @GetMapping("/holiday-list")
    public String showHolidayList(@ModelAttribute("month") int month) {
        // 月別の休日一覧を取得するロジックを実装
        return "holiday-list";
    }
}