package com.epam.preprod.traveling.domain.person;

public abstract class Person {
	
	private String firstName;
	private String secondName;
	private String email;
	private String password;
	//private String possition;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String second_name) {
		this.secondName = second_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
