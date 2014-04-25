package com.epam.preprod.traveling.domain.country;

public class Country {
	private Integer id;
	private String name;
	private String description;
	private String climate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	@Override
	public String toString() {
		return "Country {" + name + "}";
	}
}
