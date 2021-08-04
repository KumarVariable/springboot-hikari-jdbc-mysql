package com.mysql.springboot.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mysql.springboot.pojo.EmployeeInformation;
import com.mysql.springboot.repository.EmployeeRepository;
import com.mysql.springboot.service.EmployeeInformationService;

/**
 * Implementation class for interface {@link EmployeeInformationService}
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Service
public class EmployeeInformationServiceImpl
		implements
			EmployeeInformationService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeInformationServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * Get list of all active employees from the database.
	 * 
	 * @return List<EmployeeInformation>
	 */
	@Override
	public List<EmployeeInformation> getAllEmployees() {
		List<EmployeeInformation> employeeList = employeeRepository
				.getAllEmployees();

		if (employeeList == null || employeeList.size() == 0) {
			LOGGER.info(" No Employees returned by database. ");
			employeeList = new ArrayList<EmployeeInformation>();
		} else {
			LOGGER.info(employeeList.size() + "records found in database. ");
		}

		return employeeList;

	}

	/**
	 * Read an active employee from the database.
	 * 
	 * @param employeeId
	 * 
	 * @return EmployeeInformation
	 */
	@Override
	public EmployeeInformation findEmployeeById(String employeeId) {
		EmployeeInformation employeeInformation = employeeRepository
				.findEmployeeById(employeeId);

		if (ObjectUtils.isEmpty(employeeInformation)) {
			LOGGER.info(" No record returned by database for employee id = "
					+ employeeId);
			employeeInformation = new EmployeeInformation();
		} else {
			LOGGER.info(
					"Employee Records found " + employeeInformation.toString()
							+ " for employee id = " + employeeId);
		}
		return employeeInformation;
	}

	/**
	 * Insert an employee into the database.
	 * 
	 * @param EmployeeInformation
	 *            employeeInfo
	 * 
	 * @return int - number of rows inserted into database.
	 */
	@Override
	public int addEmployee(EmployeeInformation employeeInfo) {

		int rowInserted = employeeRepository.addEmployee(employeeInfo);
		LOGGER.info(rowInserted + " row inserted into database. ");
		return rowInserted;
	}

	/**
	 * Update an employee into the database.
	 * 
	 * @param employeeInfo,
	 *            employee Id
	 * 
	 * @return boolean - true if operation successful. false if operation fails.
	 */
	@Override
	public boolean updateEmployeeInformation(EmployeeInformation empInfo,
			String empId) {
		boolean isSuccess = employeeRepository
				.updateEmployeeInformation(empInfo, empId);
		LOGGER.info(" Employee Record Updated successfully  " + isSuccess);
		return isSuccess;
	}

	/**
	 * Delete(De-activate) an employee into the database. Sets
	 * employee_is_active = N in database.
	 * 
	 * @param employee
	 *            Id
	 * 
	 * @return int - number of rows updated.
	 */
	@Override
	public int deleteEmployeeInfo(String empId) {
		int rowDeleted = employeeRepository.deleteEmployeeInfo(empId);
		LOGGER.info(rowDeleted + " row deleted from database. ");
		return rowDeleted;
	}

}
