package com.els.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.els.entities.Employee;
import com.els.repositories.EmployeeRepository;
import com.els.services.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	@Mock
	private EmployeeRepository employeeRepository;
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddEmployees() {

		// given
		Employee employee1 = new Employee("1", "david", "alex");
		Employee employee2 = new Employee("2", "mickel", "alex");
		Employee employee3 = new Employee("2", "mickel", "yoyo");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);

		// when
		when(employeeRepository.saveAll(employeeList)).thenReturn(employeeList);

		List<Employee> result = employeeServiceImpl.addEmployee(employeeList).get();
		assertEquals(employeeList, result);

	}

	@Test
	public void testGetListEmployeeDistinctFirstName() {
		// given
		Employee employee1 = new Employee("1", "david", "alex");
		Employee employee2 = new Employee("2", "mickel", "alex");
		Employee employee3 = new Employee("2", "mickel", "yoyo");
		List<Employee> emlpoyeeList = new ArrayList<>();
		emlpoyeeList.add(employee1);
		emlpoyeeList.add(employee2);
		emlpoyeeList.add(employee3);
		// when
		when(employeeRepository.saveAll(emlpoyeeList)).thenReturn(emlpoyeeList);

		List<Employee> result = employeeServiceImpl.getListEmployee("firstName");

		assertTrue(result.stream().collect(Collectors.groupingBy(Employee::getFirstName)).size() <= 1);

	}

	@Test
	public void testGetListEmployeeDistinctLastName() {
		// given
		Employee employee1 = new Employee("1", "david", "alex");
		Employee employee2 = new Employee("2", "mickel", "alex");
		Employee employee3 = new Employee("2", "mickel", "yoyo");
		List<Employee> emlpoyeeList = new ArrayList<>();
		emlpoyeeList.add(employee1);
		emlpoyeeList.add(employee2);
		emlpoyeeList.add(employee3);
		// when
		when(employeeRepository.saveAll(emlpoyeeList)).thenReturn(emlpoyeeList);

		List<Employee> result = employeeServiceImpl.getListEmployee("lastName");
		assertTrue(result.stream().collect(Collectors.groupingBy(Employee::getLastName)).size() <= 1);

	}
}
