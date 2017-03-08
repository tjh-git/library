package com.simple.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.example.service.IRefundInfoService;


@Controller
public class TestController {
	
	@Autowired
	private IRefundInfoService refundInfoService;
	
	@RequestMapping(value="test/test", method = RequestMethod.GET)
	public String testInfo(Model model, HttpServletRequest req) {
		return "example/test/Newtest";
	}
	
}
