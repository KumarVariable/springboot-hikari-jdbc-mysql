/* Create database 'institute' */
CREATE DATABASE institute;

/* Select database to use  */
USE institute;

/* Create table 'employees' */
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(20) NOT NULL,
  `employee_firstname` varchar(50) NOT NULL,
  `employee_lasttname` varchar(50) DEFAULT '',
  `employee_email` varchar(50) NOT NULL,
  `employee_mobile_contact` int(20) DEFAULT '0',
  `employee_is_active` varchar(5) DEFAULT 'N',
  `employee_joining_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `employee_leaving_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id` (`employee_id`),
  UNIQUE KEY `employee_email` (`employee_email`)
);

/*Insert record(s) into 'employees' table */

INSERT INTO `institute`.`employees`
(`id`,
`employee_id`,
`employee_firstname`,
`employee_lasttname`,
`employee_email`,
`employee_mobile_contact`,
`employee_is_active`,
`employee_joining_date`,
`employee_leaving_date`)
VALUES
(<{id: }>,
<{employee_id: }>,
<{employee_firstname: }>,
<{employee_lasttname: }>,
<{employee_email: }>,
<{employee_mobile_contact: 0}>,
<{employee_is_active: N}>,
<{employee_joining_date: CURRENT_TIMESTAMP}>,
<{employee_leaving_date: CURRENT_TIMESTAMP}>);


/* Select all employees. */

SELECT `employees`.`id`,
    `employees`.`employee_id`,
    `employees`.`employee_firstname`,
    `employees`.`employee_lasttname`,
    `employees`.`employee_email`,
    `employees`.`employee_mobile_contact`,
    `employees`.`employee_is_active`,
    `employees`.`employee_joining_date`,
    `employees`.`employee_leaving_date`
FROM `institute`.`employees`;


/* Select all employees whose status is active = 'Y' */

SELECT `employees`.`id`,
    `employees`.`employee_id`,
    `employees`.`employee_firstname`,
    `employees`.`employee_lasttname`,
    `employees`.`employee_email`,
    `employees`.`employee_mobile_contact`,
    `employees`.`employee_is_active`,
    `employees`.`employee_joining_date`,
    `employees`.`employee_leaving_date`
FROM `institute`.`employees`
WHERE employee_is_active = <{expr}>;


/* Update employee information by employee id = <{id: }> */

UPDATE `institute`.`employees`
SET
`id` = <{id: }>,
`employee_id` = <{employee_id: }>,
`employee_firstname` = <{employee_firstname: }>,
`employee_lasttname` = <{employee_lasttname: }>,
`employee_email` = <{employee_email: }>,
`employee_mobile_contact` = <{employee_mobile_contact: 0}>,
`employee_is_active` = <{employee_is_active: N}>,
`employee_joining_date` = <{employee_joining_date: CURRENT_TIMESTAMP}>,
`employee_leaving_date` = <{employee_leaving_date: CURRENT_TIMESTAMP}>
WHERE `id` = <{expr}>;

/* Delete employee information by employee id = <{id: }> */

DELETE FROM `institute`.`employees`
WHERE <{where_expression}>;


