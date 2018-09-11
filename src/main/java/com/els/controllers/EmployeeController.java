package com.els.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.els.entities.Employee;
import com.els.services.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private final EmployeeServiceImpl employeeServiceImpl;

	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllUsers(@RequestParam("criteria") String criteria) {
		List<Employee> employees = employeeServiceImpl.getListEmployee(criteria);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
}
