package com.epam.preprod.traveling.domain.hottel;

import com.epam.preprod.traveling.domain.country.Country;

public class Hottel {
	private Integer id;
	private Country country;
	private String name;
	private int stars;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Hottel [id=" + id + ", country=" + country + ", name=" + name
				+ ", stars=" + stars + ", description=" + description + "]";
	}
}
