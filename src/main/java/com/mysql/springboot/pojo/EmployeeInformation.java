package com.mysql.springboot.pojo;

/**
 * An Employee
 * 
 * @author metanoia
 * @version 1.0
 */
public class EmployeeInformation {

	private int primaryId;
	private String employeeId;
	private String empFirstname;
	private String empLastName;
	private String empEmail;
	private int empMobileContact;
	private String empJoiningDate;
	private String empLeavingDate;
	private String isEmployeeActive;

	public int getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpFirstname() {
		return empFirstname;
	}
	public void setEmpFirstname(String empFirstname) {
		this.empFirstname = empFirstname;
	}

	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public int getEmpMobileContact() {
		return empMobileContact;
	}
	public void setEmpMobileContact(int empMobileContact) {
		this.empMobileContact = empMobileContact;
	}

	public String getEmpJoiningDate() {
		return empJoiningDate;
	}
	public void setEmpJoiningDate(String empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}

	public String getEmpLeavingDate() {
		return empLeavingDate;
	}
	public void setEmpLeavingDate(String empLeavingDate) {
		this.empLeavingDate = empLeavingDate;
	}

	public String getIsEmployeeActive() {
		return isEmployeeActive;
	}
	public void setIsEmployeeActive(String isEmployeeActive) {
		this.isEmployeeActive = isEmployeeActive;
	}

	@Override
	public String toString() {
		return "EmployeeInformation [primaryId=" + primaryId + ", employeeId="
				+ employeeId + ", empFirstname=" + empFirstname
				+ ", empLastName=" + empLastName + ", empEmail=" + empEmail
				+ ", empMobileContact=" + empMobileContact + ", empJoiningDate="
				+ empJoiningDate + ", empLeavingDate=" + empLeavingDate
				+ ", isEmployeeActive=" + isEmployeeActive + "]";
	}

}
