package com.epam.preprod.traveling.repository.implementation.hsql.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.epam.preprod.traveling.domain.booking.Booking;
import com.epam.preprod.traveling.repository.analitic.AnaliticRepository;
import com.epam.preprod.traveling.repository.booking.BookingRepository;
import com.epam.preprod.traveling.repository.implementation.hsql.analitic.AnaliticRepositoryHsql;
import com.epam.preprod.traveling.repository.implementation.hsql.tour.TourRepositoryHsql;
import com.epam.preprod.traveling.repository.implementation.hsql.user.UserRepositoryHsql;
import com.epam.preprod.traveling.repository.tour.TourRepository;
import com.epam.preprod.traveling.repository.user.UserRepository;

@Repository("bookingRepository")
public class BookingRepositoryHsql implements BookingRepository{

	private DataSource ds;

    public BookingRepositoryHsql(DataSource ds) {
        this.ds = ds;
    }
	
	public Booking findByName(String name) {
		throw new UnsupportedOperationException();
	}

	public Booking findById(int id) {
		throw new UnsupportedOperationException();
	}

	public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("select * from booking");
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	
            while (resultSet.next()) {
            	bookings.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return bookings;
	}

	public boolean add(Booking booking) {

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO booking (tour_id, ordered_by, status, managed_by, total_price) VALUES (?, ?, ?, ?, ?)");) {
        	preparedStatement.setInt(1, booking.getTour().getId());
        	preparedStatement.setInt(2, booking.getOrderedBy().getId());
        	preparedStatement.setString(3, booking.getStatus());
        	preparedStatement.setInt(4, booking.getManagedBy().getId());
        	preparedStatement.setFloat(5, booking.getTotalPrice());
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	private Booking map(ResultSet resultSet) throws SQLException {
		
		Booking booking = new Booking();
		booking.setId(resultSet.getInt("id"));
		
		TourRepository tourRepository = new TourRepositoryHsql(ds);
		booking.setTour(tourRepository.findById(resultSet.getInt("tour_id")));
		
		UserRepository userRepository = new UserRepositoryHsql(ds);
		booking.setOrderedBy(userRepository.findById(resultSet.getInt("ordered_by")));
		booking.setStatus(resultSet.getString("status"));
		
		AnaliticRepository analiticRepository = new AnaliticRepositoryHsql(ds);
		booking.setManagedBy(analiticRepository.findById(resultSet.getInt("managed_by")));
		booking.setTotalPrice(resultSet.getFloat("total_price"));
		
        return booking;
	}

}
