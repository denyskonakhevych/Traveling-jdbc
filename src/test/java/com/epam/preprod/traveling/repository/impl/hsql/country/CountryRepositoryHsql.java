package com.epam.preprod.traveling.repository.impl.hsql.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.repository.country.CountryRepository;

@Repository("countryRepository")
public class CountryRepositoryHsql implements CountryRepository{

	private DataSource ds;

    public CountryRepositoryHsql(DataSource ds) {
        this.ds = ds;
    }
	
	public Country findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Country findById(int id) {
		try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from country WHERE id = " + id);
        	ResultSet resultSet = preparedStatement.executeQuery();) {
			if (resultSet.next()) {
				return map(resultSet);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<Country> findAll() {
        List<Country> countries = new ArrayList<Country>();

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("select * from country");
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	
            while (resultSet.next()) {
            	countries.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return countries;
	}

	private Country map(ResultSet resultSet) throws SQLException {
		
		Country country = new Country();
		country.setId(resultSet.getInt("id"));
		country.setName(resultSet.getString("name"));
		country.setDescription(resultSet.getString("description"));
		country.setClimate(resultSet.getString("climate"));
		
        return country;
	}

	public boolean add(Country country) {

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country(name, description, climate) VALUES (?, ?, ?)");) {
        	preparedStatement.setString(1, country.getName());
        	preparedStatement.setString(2, country.getDescription());
        	preparedStatement.setString(3, country.getClimate());
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
}
