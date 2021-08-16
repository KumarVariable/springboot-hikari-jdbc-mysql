package com.mysql.springboot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import com.mysql.springboot.pojo.EmployeeInformation;
import com.mysql.springboot.service.EmployeeInformationService;

/**
 * Main class having public static void main() method that starts up the Spring
 * Application context.It is the class that runs for the execution of the
 * application.
 * 
 * @author metanoia
 * @version 1.0
 */

@SpringBootApplication
public class BootHikariMySqlApplication implements CommandLineRunner {

    /**
     * @SpringBootApplication annotation is equivalent
     *                        to @Configuration, @EnableAutoConfiguration,
     *                        and @ComponentScan with their default attributes.
     * 
     * @CommandLineRunner an interface with a run method.The interface is used to
     *                    run a code block only once in a application's lifetime -
     *                    after application is initialized.
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(BootHikariMySqlApplication.class);

    // To display all loaded beans
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    EmployeeInformationService employeeService;

    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
	LOGGER.info("Starting BootHikariMySqlApplication ");
	SpringApplication.run(BootHikariMySqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

	Map<String, DataSource> map = appContext.getBeansOfType(DataSource.class);

	LOGGER.info("Loaded Datasource Name ::  " + map.get("dataSource"));

	LOGGER.info(
		"Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.",
		Arrays.toString(args));

	LOGGER.info("Uncomment (if not) code to Call service to Insert Employee into database.");

	EmployeeInformation employeeInformation = dummyEmployee();

	int rowInserted = employeeService.addEmployee(employeeInformation);

	LOGGER.info(rowInserted + " Record inserted successfully ");

	String empId = "";

	LOGGER.info("Read all active employees ");
	List<EmployeeInformation> employeeInfoList = employeeService.getAllEmployees();

	if (employeeInfoList.size() > 0) {
	    for (EmployeeInformation info : employeeInfoList) {
		LOGGER.info("Employee Record :: " + info.toString());
		empId = info.getEmployeeId();
	    }
	}

	LOGGER.info("Read employee information by employee Id " + empId);

	EmployeeInformation empInformation = employeeService.findEmployeeById(empId);

	if (!ObjectUtils.isEmpty(employeeInfoList)) {
	    LOGGER.info("Record of employee id " + empInformation.toString());
	}

	LOGGER.info("Uncomment code to perform Update operation");

	boolean isUpdateSuccess = employeeService.updateEmployeeInformation(dummyUpdateEmployee(empId), empId);

	String logMessage = String.format("Employee update operation %s for employee Id = %s ", isUpdateSuccess, empId);

	LOGGER.info(logMessage);

	LOGGER.info("Delete (De-acivate Employee");

	int recordDeleted = employeeService.deleteEmployeeInfo(empId);

	String logMsg = String.format(
		"Employee delete operation %s record status changed to inactive for employee Id = %s ", recordDeleted,
		empId);

	LOGGER.info(logMsg);

    }

    /**
     * Helper method to supply dummy employee to insert into database.
     */
    public static EmployeeInformation dummyEmployee() {

	Date date = Calendar.getInstance().getTime();

	String stringDate = DATE_FORMATTER.format(date);

	EmployeeInformation employeeInformation = new EmployeeInformation();

	LocalDateTime now = LocalDateTime.now();
	int second = now.getSecond();

	String strTime = String.valueOf(second);

	employeeInformation.setEmployeeId("SG-".concat(strTime));
	employeeInformation.setEmpFirstname("KUN".concat(strTime));
	employeeInformation.setEmpLastName("KUMAR".concat(strTime));
	employeeInformation.setEmpEmail("k".concat(strTime).concat("@dummyemail.com"));
	employeeInformation.setEmpMobileContact(second);
	employeeInformation.setIsEmployeeActive("Y");

	employeeInformation.setEmpJoiningDate(stringDate);
	employeeInformation.setEmpLeavingDate(stringDate);

	return employeeInformation;

    }

    /**
     * Helper method to supply dummy employee to update into database.
     * 
     * @param empId
     */
    public static EmployeeInformation dummyUpdateEmployee(String empId) {

	Date date = Calendar.getInstance().getTime();

	String stringDate = DATE_FORMATTER.format(date);

	EmployeeInformation employeeInformation = new EmployeeInformation();

	employeeInformation.setEmployeeId(empId);
	employeeInformation.setEmpFirstname("UPDATE".concat(empId));
	employeeInformation.setEmpLastName("KUMAR");
	employeeInformation.setEmpEmail("kkr".concat(empId).concat("@indianemail.com"));
	employeeInformation.setEmpMobileContact(99999);
	employeeInformation.setIsEmployeeActive("Y");

	employeeInformation.setEmpJoiningDate(stringDate);
	employeeInformation.setEmpLeavingDate(stringDate);

	return employeeInformation;

    }
}
