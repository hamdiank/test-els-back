package com.els.services;

import java.util.List;
import java.util.Optional;

import com.els.entities.Employee;

public interface EmployeeService {

	List<Employee> getListEmployee(String criteria);

	Optional<List<Employee>>  addEmployee(List<Employee> employee);

}
