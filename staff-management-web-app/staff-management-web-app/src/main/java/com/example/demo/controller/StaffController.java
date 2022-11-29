package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Staff;
import com.example.demo.service.StaffService;

@Controller
public class StaffController {
	@Autowired
	private StaffService staffService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listStaffs", staffService.getAllStaffs());
		return "index";
	}
	
	@GetMapping("/showNewStaffForm")
	public String showNewStaffForm(Model model) {
		Staff staff = new Staff();
		model.addAttribute("staff" ,staff);
		return "new_staff";
	}
	
	@PostMapping("/saveStaff")
	public String saveStaff(@ModelAttribute("staff") Staff staff) {
		staffService.saveStaff(staff);
		return "redirect:/";
	}
}
