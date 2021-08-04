package com.mysql.springboot.service;

import java.util.List;

import com.mysql.springboot.pojo.EmployeeInformation;

/**
 * Service interface with abstract APIs to provide logic to operate on the data
 * sent to and from the repository layer and the client.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

public interface EmployeeInformationService {

	public List<EmployeeInformation> getAllEmployees();

	public EmployeeInformation findEmployeeById(String employeeId);

	public int addEmployee(EmployeeInformation employeeInfo);

	public boolean updateEmployeeInformation(EmployeeInformation empInfo,
			String empId);

	public int deleteEmployeeInfo(String empId);

}
