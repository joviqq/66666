package com.freepaay.manage.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freepaay.exception.FreepaayException;
import com.itextpdf.text.DocumentException;

@Controller
public class TbUserController {
	@RequestMapping(value = "/admin/list")
	public String createPDFBycntKey(HttpSession session, HttpServletRequest request, Model model, 
			@PathVariable String cntKey
			) throws FreepaayException, MalformedURLException, DocumentException, IOException {
		return "/manage/PDF/createPDF";
	}
	
}