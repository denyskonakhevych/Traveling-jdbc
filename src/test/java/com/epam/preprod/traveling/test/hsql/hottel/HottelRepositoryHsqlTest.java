package com.epam.preprod.traveling.test.hsql.hottel;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.domain.hottel.Hottel;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;
import com.epam.preprod.traveling.test.hsql.DaoTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class HottelRepositoryHsqlTest extends DaoTestsTemplate {

	@Autowired
    private HottelRepository hottelRepository;
	@Autowired
    private CountryRepository countryRepository;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate.execute("TRUNCATE TABLE country");
		jdbcTemplate.execute("TRUNCATE TABLE hottel");
	}
	@Ignore
	@Test
	public void test() {
		
		Country tmpCountry = new Country(null, "Ukraine", "Best country", "Mild");
		countryRepository.add(tmpCountry);
		Country country = countryRepository.findAll().get(0);
		
		Hottel hottel = new Hottel(null, country, "Hyatt Regency", 5, "The Hyatt Regency Kiev is the 5 star hotel located in the city centre of Kyiv");
		
		hottelRepository.add(hottel);
		List<Hottel> hottels = hottelRepository.findAll();
		for (Hottel currentHottel : hottels) {
			System.out.println(currentHottel);
		}
	}

}
