//package com.example.attendance.controller;
//
//import java.sql.Date;
//import java.util.Calendar;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.example.attendance.entity.Application;
//import com.example.attendance.form.ApplicationForm;
//import com.example.attendance.service.ApplicationService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class ApplicationController {
//
//	private final ApplicationService service;
//
//	@GetMapping("/application")
//	private String request(@ModelAttribute ApplicationForm form) {
//		return "request";
//	}
//
//	// 申請の可否はjavascript側で判断してもらうか→攻撃の可能性
//	@PostMapping("/api/submit-request")
//	private String application(@Validated @ModelAttribute ApplicationForm form, BindingResult result,
//			RedirectAttributes redirectAttributes) {
//		System.out.println("Received form: " + form);
//		if (result.hasErrors()) {
//			return "request?error";
//		}
//
//		Date startDate = form.getStartDate();
//		Date endDate = form.getEndDate();
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(startDate);
//
//
//	    while (!calendar.getTime().after(endDate)) {
//	        Application a = new Application();
//	        a.setEmployeeId("111111"); // 仮置き
//	        a.setCategoryId(form.getCategoryId());
//	        a.setComment(form.getComment());
//	        a.setDateApply(new Date(calendar.getTimeInMillis()));
//	        service.regist(a);
//
//	        calendar.add(Calendar.DAY_OF_MONTH, 1);
//	    }
//
//		redirectAttributes.addFlashAttribute("msg", "申請が完了しました！");
//
//		return "request";
//	}
//}
//


package com.example.attendance.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.attendance.entity.Application;
import com.example.attendance.form.ApplicationForm;
import com.example.attendance.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @GetMapping("/application")
    private String request(@ModelAttribute ApplicationForm form) {
        return "request";
    }

    @PostMapping("/api/submit-request")
    @ResponseBody
    public ResponseEntity<String> application(@Validated @ModelAttribute ApplicationForm form, BindingResult result) {
        System.out.println("Received form: " + form);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("エラー: フォームの検証に失敗しました。");
        }

        Date startDate = form.getStartDate();
        Date endDate = form.getEndDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            Application a = new Application();
            a.setEmployeeId("111111"); // 仮置き
            a.setCategoryId(form.getCategoryId());
            a.setComment(form.getComment());
            a.setDateApply(new Date(calendar.getTimeInMillis()));
            service.regist(a);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return ResponseEntity.ok("申請が正常に送信されました。");
    }
}