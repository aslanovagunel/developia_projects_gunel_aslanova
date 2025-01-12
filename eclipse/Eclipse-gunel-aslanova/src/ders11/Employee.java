package ders11;

public class Employee extends Person {

	private String salary;
	private String department;
	private String username;
	private String password;

	public Employee() {
		super();
	}

	public Employee(int id, String name, String surName, int age, String phone, String address, String salary,
			String department, String username, String password) {
		super(id, name, surName, age, phone, address);
		this.salary = salary;
		this.department = department;
		this.username = username;
		this.password = password;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	void printInfo() {
		super.printInfo();
		System.out.println("salary: " + salary + "\ndepartment: " + department + "\nusername: " + username
				+ "\npassword: " + password);
	}

}
