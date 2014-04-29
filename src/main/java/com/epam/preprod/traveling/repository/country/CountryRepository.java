package com.epam.preprod.traveling.repository.country;

import java.util.List;

import com.epam.preprod.traveling.domain.country.Country;

public interface CountryRepository {

	Country findByName(String name);
	Country findById(int id);
	List<Country> findAll();
	boolean add(Country country);
}
