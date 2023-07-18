package com.spring.springmvcdemo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.spring.springmvcdemo.dao.AlienDao;
import com.spring.springmvcdemo.model.Alien;

@Controller
public class HomeController {
	@Autowired
	AlienDao dao;
	
	@ModelAttribute
	public void modelData(Model m) {
		m.addAttribute("name","Aliens");
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	@RequestMapping("add")
	public String add(@RequestParam("num1") int i,@RequestParam("num2") int j,ModelMap m) {
		
		//ModelAndView view = new ModelAndView("result");
		int num3 = i+j;
		m.addAttribute("num3", num3);
		return "result";
	}
	@GetMapping("getAliens")
	public String getAliens(Model m) {
	
		m.addAttribute("result", dao.getAliens());
		return "showAliens";
		
	}
	@PostMapping("addAlien")
	public String addAlien(@ModelAttribute Alien a,Model m) {
		/*
		 * We can add object data without explicitly called setter using ModelAttribute it will
		 * automatic add those things required to an object
		 */
		return "result";
		
	}
}
