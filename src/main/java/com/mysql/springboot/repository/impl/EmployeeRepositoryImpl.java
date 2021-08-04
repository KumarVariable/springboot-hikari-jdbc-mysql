package com.mysql.springboot.repository.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.mysql.springboot.pojo.EmployeeInformation;
import com.mysql.springboot.repository.EmployeeRepository;
import com.mysql.springboot.resultset.mapper.EmployeeInformationMapper;

/**
 * Implementation of Database operation's APIs. Class is responsible to
 * communicate to database and persist data, modify data through database
 * operations.
 * 
 * @author metanoia
 * @version 1.0
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String INSERT_EMPLOYEE = " INSERT INTO employees (employee_id, employee_firstname, employee_lasttname, "
			+ " employee_email, employee_mobile_contact, employee_is_active, employee_joining_date, employee_leaving_date) "
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

	private static final String FIND_ALL_EMPLOYEES = "SELECT id, employee_id, employee_firstname, employee_lasttname, employee_email, "
			+ " employee_mobile_contact, employee_is_active, employee_joining_date, "
			+ " employee_leaving_date FROM employees WHERE employee_is_active = 'Y' ";

	private static final String FIND_EMPLOYEE_BY_ID = "SELECT id, employee_id, employee_firstname, employee_lasttname, employee_email, "
			+ " employee_mobile_contact, employee_is_active, employee_joining_date, "
			+ " employee_leaving_date FROM employees WHERE employee_id = ? AND employee_is_active = ? ";

	private static final String UPDATE_EMP_INFO_BY_ID = "UPDATE employees SET employee_id = ?, employee_firstname = ?, employee_lasttname = ?, "
			+ " employee_email = ?, employee_mobile_contact = ?, employee_is_active = ?, employee_joining_date = ?, "
			+ " employee_leaving_date = ? WHERE employee_id = ? ";

	private static final String DELETE_EMP_INFO_BY_ID = "UPDATE employees SET employee_is_active = ? WHERE employee_id = ? ";

	@Override
	public List<EmployeeInformation> getAllEmployees() {

		List<EmployeeInformation> employeeList = jdbcTemplate
				.query(FIND_ALL_EMPLOYEES, new EmployeeInformationMapper());

		return employeeList;
	}

	@Override
	public EmployeeInformation findEmployeeById(String employeeId) {
		String isActive = "Y";
		Object[] params = {employeeId, isActive};

		EmployeeInformation employeeInfo = jdbcTemplate.queryForObject(
				FIND_EMPLOYEE_BY_ID, new EmployeeInformationMapper(), params);
		return employeeInfo;
	}

	@Override
	public int addEmployee(EmployeeInformation employeeInfo) {

		int insertCount = 0;

		insertCount = jdbcTemplate.execute(INSERT_EMPLOYEE,
				new PreparedStatementCallback<Integer>() {

					@Override
					public Integer doInPreparedStatement(
							PreparedStatement preparedStatement)
							throws SQLException, DataAccessException {

						preparedStatement.setString(1,
								employeeInfo.getEmployeeId());
						preparedStatement.setString(2,
								employeeInfo.getEmpFirstname());
						preparedStatement.setString(3,
								employeeInfo.getEmpLastName());
						preparedStatement.setString(4,
								employeeInfo.getEmpEmail());
						preparedStatement.setInt(5,
								employeeInfo.getEmpMobileContact());
						preparedStatement.setString(6,
								employeeInfo.getIsEmployeeActive());

						Date joiningDate = getSQLDate(
								employeeInfo.getEmpJoiningDate());
						Date leavingDate = getSQLDate(
								employeeInfo.getEmpLeavingDate());

						preparedStatement.setDate(7, joiningDate);
						preparedStatement.setDate(8, leavingDate);

						return preparedStatement.executeUpdate();

					}
				});

		return insertCount;
	}

	@Override
	public boolean updateEmployeeInformation(EmployeeInformation empInfo,
			String empId) {

		boolean rowUpdated = Boolean.FALSE;

		String employeeId = empInfo.getEmployeeId();
		String employeeFirstName = empInfo.getEmpFirstname();
		String employeeLastName = empInfo.getEmpLastName();
		String employeeEmail = empInfo.getEmpEmail();
		int employeeMobileContact = empInfo.getEmpMobileContact();
		String employeeIsActive = empInfo.getIsEmployeeActive();

		Date sqlJoiningDate = getSQLDate(empInfo.getEmpJoiningDate());
		Date sqlLeavingDate = getSQLDate(empInfo.getEmpLeavingDate());

		Object[] arguments = {employeeId, employeeFirstName, employeeLastName,
				employeeEmail, employeeMobileContact, employeeIsActive,
				sqlJoiningDate, sqlLeavingDate, employeeId};

		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP, Types.VARCHAR};

		int count = jdbcTemplate.update(UPDATE_EMP_INFO_BY_ID, arguments,
				types);

		if (count > 0) {
			rowUpdated = Boolean.TRUE;
		}

		return rowUpdated;
	}

	@Override
	public int deleteEmployeeInfo(String employeeId) {
		String isActive = "N";
		int inactiveRecord = jdbcTemplate.update(DELETE_EMP_INFO_BY_ID,
				new Object[]{isActive, employeeId});
		return inactiveRecord;
	}

	/**
	 * Private helper method to convert String date to java.sql.Date
	 * 
	 * @param String
	 *            date
	 * 
	 * @return Date
	 * 
	 */
	private Date getSQLDate(String date) {
		Date sqlDate = Date.valueOf(date);
		return sqlDate;
	}

}
