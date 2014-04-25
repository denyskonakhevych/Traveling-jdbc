package com.epam.preprod.traveling.domain.analitic;

import com.epam.preprod.traveling.domain.person.Person;

public class Analitic extends Person{
	private Integer id;
	private String possition;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPossition() {
		return possition;
	}

	public void setPossition(String possition) {
		this.possition = possition;
	}
}
