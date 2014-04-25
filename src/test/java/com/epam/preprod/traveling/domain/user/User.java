package com.epam.preprod.traveling.domain.user;

import java.util.List;

import com.epam.preprod.traveling.domain.person.Person;

public class User extends Person{
	private Integer id;
	private String sex;
	private List<String> telephones;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<String> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<String> telephones) {
		this.telephones = telephones;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
