package com.epam.preprod.traveling.test.hsql.booking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.analitic.Analitic;
import com.epam.preprod.traveling.domain.booking.Booking;
import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.domain.hottel.Hottel;
import com.epam.preprod.traveling.domain.tour.Tour;
import com.epam.preprod.traveling.domain.user.User;
import com.epam.preprod.traveling.repository.analitic.AnaliticRepository;
import com.epam.preprod.traveling.repository.booking.BookingRepository;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;
import com.epam.preprod.traveling.repository.tour.TourRepository;
import com.epam.preprod.traveling.repository.user.UserRepository;
import com.epam.preprod.traveling.test.hsql.DaoTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class BookingRepositoryHsqlTest extends DaoTestsTemplate {

	@Autowired
    private BookingRepository bookingRepository;
	@Autowired
    private TourRepository tourRepository;
	@Autowired
    private HottelRepository hottelRepository;
	@Autowired
    private CountryRepository countryRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private AnaliticRepository analiticRepository;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate.execute("TRUNCATE TABLE country");
		jdbcTemplate.execute("TRUNCATE TABLE hottel");
		jdbcTemplate.execute("TRUNCATE TABLE tour");
		jdbcTemplate.execute("TRUNCATE TABLE analitic");
		jdbcTemplate.execute("TRUNCATE TABLE user");
	}

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
		
		Tour tmpTour = new Tour(null, 2, from.getTime(), to.getTime(), hottel, 14999.0f);
		tourRepository.add(tmpTour);
		Tour tour = tourRepository.findAll().get(0); 
		
		List<String> usersTelephones = new ArrayList<>();
		usersTelephones.add("+38(012)1234567");
		User tmpUser = new User("Mary", "Johnson", "mary.johnson@gmail.com", "jmary", null, "female", usersTelephones, "Mary's address");
		userRepository.add(tmpUser);
		User user = userRepository.findAll().get(0);
		
		Analitic tmpAnalitic = new Analitic("Mary", "Johnson", "mary.johnson@gmail.com", "jmary", null, "Analitic");
		analiticRepository.add(tmpAnalitic);
		Analitic analitic = analiticRepository.findAll().get(0);
		
		Booking booking = new Booking(null, tour, user, analitic, "ordered", 14999.0f);
		bookingRepository.add(booking);
		List<Booking> bookings = bookingRepository.findAll();
		for (Booking currentBooking : bookings) {
			System.out.println(currentBooking);
		}
	}

}
