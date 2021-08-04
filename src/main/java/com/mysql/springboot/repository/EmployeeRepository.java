package com.mysql.springboot.repository;

import java.util.List;

import com.mysql.springboot.pojo.EmployeeInformation;

/**
 * Abstract APIs to encapsulate the details of the persistence layer and provide
 * a CRUD interface for a single entity.
 * 
 * @author metanoia
 * @version 1.0
 */
public interface EmployeeRepository {

	public List<EmployeeInformation> getAllEmployees();

	public EmployeeInformation findEmployeeById(String employeeId);

	public int addEmployee(EmployeeInformation employeeInfo);

	public boolean updateEmployeeInformation(EmployeeInformation empInfo,
			String empId);

	public int deleteEmployeeInfo(String empId);

}
