package com.epam.preprod.traveling.test.hsql.tour;

import java.util.Calendar;
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
import com.epam.preprod.traveling.domain.tour.Tour;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;
import com.epam.preprod.traveling.repository.tour.TourRepository;
import com.epam.preprod.traveling.test.hsql.DaoTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class TourRepositoryHsqlTest extends DaoTestsTemplate {

	@Autowired
    private TourRepository tourRepository;
	@Autowired
    private HottelRepository hottelRepository;
	@Autowired
    private CountryRepository countryRepository;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate.execute("TRUNCATE TABLE country");
		jdbcTemplate.execute("TRUNCATE TABLE hottel");
		jdbcTemplate.execute("TRUNCATE TABLE tour");
	}
	@Ignore
	@Test
	public void test() {
		
		Country tmpCountry = new Country(null, "Ukraine", "best country", "Mild");
		countryRepository.add(tmpCountry);
		Country country = countryRepository.findAll().get(0);
		
		Hottel tmpHottel = new Hottel(null, country, "Hyatt Regency", 5, "The Hyatt Regency Kiev is the 5 star hotel located in the city centre of Kyiv");
		hottelRepository.add(tmpHottel);
		Hottel hottel = hottelRepository.findAll().get(0);
		
		Calendar from = Calendar.getInstance();
		from.set(Calendar.YEAR, 2014);
		from.set(Calendar.MONTH, 7);
		from.set(Calendar.DAY_OF_MONTH, 1);
		Calendar to = Calendar.getInstance();
		to.set(Calendar.YEAR, 2014);
		to.set(Calendar.MONTH, 7);
		to.set(Calendar.DAY_OF_MONTH, 13);
		
		Tour tour = new Tour(null, 2, from.getTime(), to.getTime(), hottel, 14999.0f);
		
		tourRepository.add(tour);
		List<Tour> tours = tourRepository.findAll();
		for (Tour currentTour : tours) {
			System.out.println(currentTour);
		}
	}

}
