package com.epam.preprod.traveling.repository.implementation.hsql.analitic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.epam.preprod.traveling.domain.analitic.Analitic;
import com.epam.preprod.traveling.repository.analitic.AnaliticRepository;

@Repository("analiticRepository")
public class AnaliticRepositoryHsql implements AnaliticRepository{

	private DataSource ds;

    public AnaliticRepositoryHsql(DataSource ds) {
        this.ds = ds;
    }
	
	public Analitic findByName(String name) {
		throw new UnsupportedOperationException();
	}

	public Analitic findById(int id) {
		try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from analitic WHERE id = " + id);
        	ResultSet resultSet = preparedStatement.executeQuery();) {
			if (resultSet.next()) {
				return map(resultSet);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<Analitic> findAll() {
        List<Analitic> analitics = new ArrayList<Analitic>();

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("select * from analitic");
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	
            while (resultSet.next()) {
            	analitics.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return analitics;
	}

	public boolean add(Analitic analitic) {

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO analitic(name_f, name_s, email, password) VALUES (?, ?, ?, ?)");) {
        	preparedStatement.setString(1, analitic.getFirstName());
        	preparedStatement.setString(2, analitic.getSecondName());
        	preparedStatement.setString(3, analitic.getEmail());
        	preparedStatement.setString(4, analitic.getPassword());
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	private Analitic map(ResultSet resultSet) throws SQLException {
		
		Analitic analitic = new Analitic();
		analitic.setId(resultSet.getInt("id"));
		analitic.setFirstName(resultSet.getString("name_f"));
		analitic.setSecondName(resultSet.getString("name_s"));
		analitic.setEmail(resultSet.getString("email"));
		analitic.setPassword(resultSet.getString("password"));
		
        return analitic;
	}
	
}
