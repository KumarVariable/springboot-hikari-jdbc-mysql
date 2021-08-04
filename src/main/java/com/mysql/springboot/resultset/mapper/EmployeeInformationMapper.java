package com.mysql.springboot.resultset.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.springboot.pojo.EmployeeInformation;

/**
 * Class implements {@link RowMapper} for mapping rows of a ResultSet on a
 * per-row basis.
 * 
 * @author metanoia
 * @version 1.0
 */
public class EmployeeInformationMapper
		implements
			RowMapper<EmployeeInformation> {

	@Override
	public EmployeeInformation mapRow(ResultSet resultSet, int rowNum)
			throws SQLException {

		EmployeeInformation employeeInfo = new EmployeeInformation();

		employeeInfo.setPrimaryId(resultSet.getInt("id"));
		employeeInfo.setEmployeeId(resultSet.getString("employee_id"));
		employeeInfo.setEmpFirstname(resultSet.getString("employee_firstname"));
		employeeInfo.setEmpLastName(resultSet.getString("employee_lasttname"));
		employeeInfo.setEmpEmail(resultSet.getString("employee_email"));
		employeeInfo.setEmpMobileContact(
				resultSet.getInt("employee_mobile_contact"));
		employeeInfo
				.setIsEmployeeActive(resultSet.getString("employee_is_active"));

		String joiningDate = resultSet.getDate("employee_joining_date")
				.toString();
		String leavingDate = resultSet.getDate("employee_leaving_date")
				.toString();

		employeeInfo.setEmpJoiningDate(joiningDate);
		employeeInfo.setEmpLeavingDate(leavingDate);

		return employeeInfo;
	}

}
