package com.epam.preprod.traveling.test.hsql.country;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.test.hsql.DaoTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class CountryRepositoryHsqlTest extends DaoTestsTemplate {

	@Autowired
    private CountryRepository countryRepository;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate.execute("TRUNCATE TABLE country");
	}
	@Ignore
	@Test
	public void test() {
		Country country = new Country(null, "Ukraine", "best country", "Mild");
		
		countryRepository.add(country);
		List<Country> countries = countryRepository.findAll();
		for (Country currentCountry : countries) {
			System.out.println(currentCountry);
		}
	}

}
