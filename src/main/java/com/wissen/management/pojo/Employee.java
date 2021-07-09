package com.wissen.management.pojo;

public class Employee {

	private int id;
	private String name;
	private int salary;
	private int age;
	private String designation;

	public Employee() {
		super();
	}

	public Employee(int id, String name, int salary, int age, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.designation = designation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", age=" + age + ", designation="
				+ designation + "]";
	}
	
	

}
