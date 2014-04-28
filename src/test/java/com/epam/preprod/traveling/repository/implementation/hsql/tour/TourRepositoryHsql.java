package com.epam.preprod.traveling.repository.implementation.hsql.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.epam.preprod.traveling.domain.hottel.Hottel;
import com.epam.preprod.traveling.domain.tour.Tour;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;
import com.epam.preprod.traveling.repository.implementation.hsql.hottel.HottelRepositoryHsql;
import com.epam.preprod.traveling.repository.tour.TourRepository;

@Repository("tourRepository")
public class TourRepositoryHsql implements TourRepository{

	private DataSource ds;

    public TourRepositoryHsql(DataSource ds) {
        this.ds = ds;
    }
	
	public Tour findByName(String name) {
		throw new UnsupportedOperationException();
	}

	public Tour findById(int id) {
		try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from tour WHERE id = " + id);
        	ResultSet resultSet = preparedStatement.executeQuery();) {
			if (resultSet.next()) {
				return map(resultSet);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<Tour>();

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("select * from tour");
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	
            while (resultSet.next()) {
            	tours.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return tours;
	}

	public boolean add(Tour tour) {

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tour(number_of_people, date_from, date_to, hottel_id, price) VALUES (?, ?, ?, ?, ?)");) {
        	
        	preparedStatement.setInt(1, tour.getNumberOfPeople());
        	preparedStatement.setDate(2, new java.sql.Date(tour.getFrom().getTime()));
        	preparedStatement.setDate(3, new java.sql.Date(tour.getTo().getTime()));
        	preparedStatement.setInt(4, tour.getHottel().getId());
        	preparedStatement.setFloat(5, tour.getPrice());
        	
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	private Tour map(ResultSet resultSet) throws SQLException {
		
		Tour tour = new Tour();
		tour.setId(resultSet.getInt("id"));
		tour.setNumberOfPeole(resultSet.getInt("number_of_people"));
		tour.setFrom(resultSet.getDate("date_from"));
		tour.setTo(resultSet.getDate("date_to"));
		tour.setPrice(resultSet.getFloat("price"));
		
		HottelRepository hottelRepository = new HottelRepositoryHsql(ds);
		int hottelId = resultSet.getInt("hottel_id");
		Hottel hottel = hottelRepository.findById(hottelId);
		tour.setHottel(hottel);
		
        return tour;
	}
}
