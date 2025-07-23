package com.example.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.attendance.entity.Application;
import com.example.attendance.form.ApplicationForm;
import com.example.attendance.service.ApplicationService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ApplicationController {

	private final ApplicationService service;

	//申請の可否はjavascript側で判断してもらうか, Postの値は応相談
	@PostMapping("/confirm-application")
	private String application(
			@Validated @ModelAttribute ApplicationForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "application";
		}

		Application a = new Application();
		a.setEmployeeId(null);
		a.setCategoryId(null);
		a.setComment(null);
		a.setDateApply(null);
		service.regist(a);

		return "application";
	}
}
