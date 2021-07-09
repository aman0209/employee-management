package com.wissen.management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.wissen.management.dao.EmployeeDAO;
import com.wissen.management.pojo.Employee;

public class EmployeeApplication {

	private static EmployeeDAO employeeDAO = new EmployeeDAO();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException, SQLException {

		System.out.println("****Welcome to Employee Management tool****");
		System.out.println("Please selct option form below");
		
		boolean flag = false;
		do {
			printMenu();
			int input = Integer.parseInt(br.readLine());
			switch (input) {
			case 1:
				addNewEmployee();
				flag = true;
				break;
			case 2:
				findAllEmployees();
				flag = true;
				break;
			case 3:
				updateSalray();
				break;
			case 4:
				deleteSalary();
				flag = true;
				break;
			case 5:
				flag = false;
				System.out.println("Exiting......");
				break;
			default:
				System.out.println("Enter number between 1 and 5");
				flag = true;
				break;
			}

		} while(flag);

	}

	private static void printMenu() {
		System.out.println("1. Add New Employee");
		System.out.println("2. Find All Employees");
		System.out.println("3. Update Salary");
		System.out.println("4. Remove Employee");
		System.out.println("5. Exit");
	}

	private static void findAllEmployees() {
		System.out.println(employeeDAO.selectAllEmployees());
	}

	private static void deleteSalary() throws IOException, SQLException {
		int id;
		Employee employee;
		System.out.println("Enter employee id");
		id= validInteger();
		employee = employeeDAO.selectEmployee(id);
		if(employee != null) {
			employeeDAO.deleteEmployee(id);
			System.out.println("Deleted successfully");
		} else {
			System.out.println("No employee found with id: " + id);
		}
	}

	private static void updateSalray() throws IOException, SQLException {
		int id;
		int salary;
		Employee employee;
		System.out.println("Enter id");
		id= validInteger();
		System.out.println("Enter Updated salary");
		salary= validInteger();
		employee = employeeDAO.selectEmployee(id);
		if(employee != null) {
			employee.setSalary(salary);
			employeeDAO.updateEmployee(employee);
			System.out.println("Salar successfully");
		} else {
			System.out.println("No employee found with id: " + id);
		}
	}

	private static void addNewEmployee() throws IOException {
		int id;
		int age;
		int salary;
		String name;
		String desig;
		Employee employee;
		System.out.println("Enter Employee Id");
		id= validInteger();
		System.out.println("Enter Employee Name");
		name = br.readLine();
		System.out.println("Enter Employee salary");
		salary= validInteger();
		System.out.println("Enter Age");
		age= validInteger();
		System.out.println("Enter Designation");
		desig = br.readLine();
		employee = new Employee(id, name, salary, age, desig);
		try {
			employeeDAO.insertEmployee(employee);
			System.out.println("Employee added successfully");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	private static int validInteger() throws IOException {
		int id = 0;
		try {
			id = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Enter valid integer");
		} 
		return id;
	}

}
