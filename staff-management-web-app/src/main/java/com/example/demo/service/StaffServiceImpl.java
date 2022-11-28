package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;

public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public List<Staff> getAllStaffs() {
		// TODO Auto-generated method stub
		return staffRepository.findAll();
	}

}
