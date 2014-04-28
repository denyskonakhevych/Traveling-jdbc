package com.epam.preprod.traveling.test.hsql.country;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.repository.country.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class CountryRepositoryHsqlTest {

	@Autowired
    private CountryRepository countryRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Country country = new Country();
		country.setName("Ukraine");
		country.setClimate("climate");
		country.setDescription("description");
		
		countryRepository.add(country);
		List<Country> countries = countryRepository.findAll();
		for (Country currentCountry : countries) {
			System.out.println(currentCountry);
		}
	}

}
