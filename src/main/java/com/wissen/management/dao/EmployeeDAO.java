package com.wissen.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wissen.management.pojo.Employee;

public class EmployeeDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/wissen?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee"
			+ "  (eid, age, desig, name, salary) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String SELECT_ALL_EMPLOYEES = "select * from employee";
	private static final String DELETE_EMPLOYEE_SQL = "delete from employee where id = ?;";
	private static final String UPDATE_EMPLOYEE_SQL = "update users set salary = ? where id = ?;";
	private static final String SELECT_EMPLOYEE_BY_ID = "select * from employee where id =?";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<Employee> selectAllEmployees() {

		List<Employee> employees = new ArrayList<Employee>();
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("eid");
				String name = rs.getString("name");
				int salary = rs.getInt("salary");
				int age = rs.getInt("age");
				String desig = rs.getString("desig");
				employees.add(new Employee(id, name, salary, age, desig));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employees;
	}

	public boolean deleteEmployee(int id) throws SQLException {
		boolean rowDeleted = false;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rowDeleted;
	}

	public void insertEmployee(Employee employee) throws SQLException {
		System.out.println(INSERT_EMPLOYEE_SQL);
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setString(3, employee.getDesignation());
			preparedStatement.setString(4, employee.getName());
			preparedStatement.setInt(5, employee.getSalary());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public boolean updateEmployee(Employee employee) throws SQLException {
		boolean rowUpdated = false;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);
			preparedStatement.setLong(1, employee.getSalary());
			preparedStatement.setLong(2, employee.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rowUpdated;
	}

	public Employee selectEmployee(int id) {
		Employee employee = null;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int eid = rs.getInt("eid");
				String name = rs.getString("name");
				int salary = rs.getInt("salary");
				int age = rs.getInt("age");
				String desig = rs.getString("desig");
				new Employee(eid, name, salary, age, desig);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employee;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
			}
		}
	}
}
