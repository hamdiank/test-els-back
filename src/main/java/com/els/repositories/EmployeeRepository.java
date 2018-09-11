package com.els.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.els.entities.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
