package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
