package com.els.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.els.entities.Employee;
import com.els.repositories.EmployeeRepository;
import com.els.services.EmployeeService;
import com.els.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getListEmployee(String criteria) {
		if (readEmployeeFile().isPresent()) {

			List<Employee> distinctEmployee = readEmployeeFile().get().stream()
					.filter(Utils.distinctByKey(E -> Utils.getValues(E, criteria))).collect(Collectors.toList());
			return distinctEmployee;
		}
		return Collections.emptyList();
	}

	/**
	 * get data from json file
	 * 
	 * @return list Employee
	 */
	public Optional<List<Employee>> readEmployeeFile() {

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/employees.json");
		try {
			List<Employee> employees = mapper.readValue(inputStream, typeReference);
			return addEmployee(employees);

		} catch (IOException e) {
			log.debug("Unable to save employees: " + e.getMessage());
		}
		return Optional.empty();
	}

	/**
	 * save employees in db
	 */
	@Override
	public Optional<List<Employee>> addEmployee(List<Employee> employee) {
		return Optional.ofNullable(employeeRepository.saveAll(employee));
	}

}
