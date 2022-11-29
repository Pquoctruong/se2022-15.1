package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepository staffRepository;

	@Override
	public List<Staff> getAllStaffs() {
		return staffRepository.findAll();
	}

	@Override
	public void saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		this.staffRepository.save(staff);
	}

	@Override
	public Staff getStaffById(long id) {
		// TODO Auto-generated method stub
		Optional<Staff> optional = staffRepository.findById(id);
		Staff staff = null;
		if (optional.isPresent()) {
			staff = optional.get();
		} else {
			throw new RuntimeException("Staff not found by id :: " + id);
		}
		return staff;
	}
}
