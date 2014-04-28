package com.epam.preprod.traveling.test.hsql.hottel;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.domain.hottel.Hottel;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class HottelRepositoryHsqlTest {

	@Autowired
    private HottelRepository hottelRepository;
	@Autowired
    private CountryRepository countryRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		Country country = new Country();
		country.setName("Ukraine");
		country.setDescription("description");
		country.setClimate("climate");
		countryRepository.add(country);
		
		Hottel hottel = new Hottel();
		hottel.setCountry(countryRepository.findAll().get(0));
		hottel.setDescription("description");
		hottel.setName("hottel");
		hottel.setStars(5);
		
		hottelRepository.add(hottel);
		List<Hottel> hottels = hottelRepository.findAll();
		for (Hottel currentHottel : hottels) {
			System.out.println(currentHottel);
		}
	}

}
