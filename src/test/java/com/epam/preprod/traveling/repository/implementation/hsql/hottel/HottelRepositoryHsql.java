package com.epam.preprod.traveling.repository.implementation.hsql.hottel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.domain.hottel.Hottel;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;
import com.epam.preprod.traveling.repository.implementation.hsql.country.CountryRepositoryHsql;

@Repository("hottelRepository")
public class HottelRepositoryHsql implements HottelRepository{

	private DataSource ds;

    public HottelRepositoryHsql(DataSource ds) {
        this.ds = ds;
    }
	
	public Hottel findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Hottel findById(int id) {
		try(Connection connection = ds.getConnection();
	        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from hottel WHERE id = " + id);
	        	ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					return map(resultSet);
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return null;
	}

	public List<Hottel> findAll() {
        List<Hottel> hottels = new ArrayList<Hottel>();

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("select * from hottel");
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	
            while (resultSet.next()) {
            	hottels.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return hottels;
	}

	public boolean add(Hottel hottel) {

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO hottel(name, stars, country_id, description) VALUES (?, ?, ?, ?)");) {
        	preparedStatement.setString(1, hottel.getName());
        	preparedStatement.setInt(2, hottel.getStars());
        	preparedStatement.setInt(3, hottel.getCountry().getId());
        	preparedStatement.setString(4, hottel.getDescription());
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	private Hottel map(ResultSet resultSet) throws SQLException {
		
		Hottel hottel = new Hottel();
		hottel.setId(resultSet.getInt("id"));
		hottel.setName(resultSet.getString("name"));
		hottel.setStars(resultSet.getInt("stars"));
		hottel.setDescription(resultSet.getString("description"));
		
		CountryRepository countryRepository = new CountryRepositoryHsql(ds);
		int countryId = resultSet.getInt("country_id");
		Country country = countryRepository.findById(countryId);
		hottel.setCountry(country);
		
		return hottel;
	}
	
}
