package com.epam.preprod.traveling.repository.implementation.hsql.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.epam.preprod.traveling.domain.user.User;
import com.epam.preprod.traveling.repository.user.UserRepository;

@Repository("userRepository")
public class UserRepositoryHsql implements UserRepository{

	private DataSource ds;

    public UserRepositoryHsql(DataSource ds) {
        this.ds = ds;
    }
	
	public User findByName(String name) {
		throw new UnsupportedOperationException();
	}

	public User findById(int id) {
		try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user WHERE id = " + id);
        	ResultSet resultSet = preparedStatement.executeQuery();) {
			if (resultSet.next()) {
				return map(resultSet);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<User> findAll() {
        List<User> users = new ArrayList<User>();

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
        	ResultSet resultSet = preparedStatement.executeQuery();) {
        	
            while (resultSet.next()) {
            	users.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return users;
	}

	public boolean add(User user) {

        try(Connection connection = ds.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name_f, name_s, sex, email, password, telephone, address) VALUES (?, ?, ?, ?, ?, ?, ?)");) {
        	preparedStatement.setString(1, user.getFirstName());
        	preparedStatement.setString(2, user.getSecondName());
        	preparedStatement.setString(3, user.getSex());
        	preparedStatement.setString(4, user.getEmail());
        	preparedStatement.setString(5, user.getPassword());
        	preparedStatement.setString(6, (String)user.getTelephones().get(0)); // TODO: save list
        	preparedStatement.setString(7, user.getAddress());
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	

	private User map(ResultSet resultSet) throws SQLException {
		
		User user = new User();
		
		user.setId(resultSet.getInt("id"));
		user.setFirstName(resultSet.getString("name_f"));
		user.setSecondName(resultSet.getString("name_s"));
		user.setSex(resultSet.getString("sex"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		ArrayList<String> telephones = new ArrayList<>();
		telephones.add(resultSet.getString("telephone"));
		user.setTelephones(telephones);
		user.setAddress(resultSet.getString("address"));
		
        return user;
	}
	
}
