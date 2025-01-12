package ders11;

public class Person {

	private int id;
	private String name;
	private String surName;
	private int age;
	private String phone;
	private String address;

	public Person() {
		super();
	}

	public Person(int id, String name, String surName, int age, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.age = age;
		this.phone = phone;
		this.address = address;
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

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	void printInfo() {
		System.out.println("id: " + id + "\nname :" + name + "\nsurname :" + surName + "\nage: " + age + "\nphone: "
				+ phone + "\naddress: " + address);
	}
}
