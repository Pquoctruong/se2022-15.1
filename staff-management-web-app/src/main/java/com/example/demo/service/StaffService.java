package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Staff;

public interface StaffService {
	List<Staff> getAllStaffs();
	void saveStaff(Staff staff);
	Staff getStaffById(long id);
	void deleteStaffById(long id);
}
