package com.revature.daos;

import com.revature.util.ConnectionUtil;
import com.revature.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserPostgres implements UserDao{

	@Override
	public int createUser(User user) {

		int createId = -1; 																		/* set -1 to default, if -1 is 
																								   returned userId was not created */
		String sql = "insert into users(username, password) values(?, ?) returning id;";		// command for adding username/password to DB
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, user.getUsername());
			prepStmt.setString(1, user.getPassword());
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if (resSet.next()) {
				createId = resSet.getInt("id");
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return createId;
	}

	@Override
	public List<User> getUsers() {
		
		String sql = "select * from users;";
		List<User> users = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			Statement stmt = conn.createStatement();
			ResultSet resSet = stmt.executeQuery(sql);
			
			while(resSet.next()) {
				User newUser = new User();
				newUser.setId(resSet.getInt("id"));
				newUser.setUsername(resSet.getString("username"));
				newUser.setPassword(resSet.getString("password"));
				
				users.add(newUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return users;
	}

	@Override
	public User getUserById(int id) {

		String sql = "select * from users where id = ?;";
		User user = null;
		
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setInt(1, id); // this refers to the 1st ? in the sql String
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if(resSet.next()) {
				user = new User();
				user.setId(resSet.getInt("id"));
				user.setUsername(resSet.getString("username"));
				user.setPassword(resSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return user;
	}

	@Override
	public User getUserByName(String username) {
		
		String sql = "select * from users where username = ?;";
		User user = null;
		
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, username);                                             // this refers to the 1st ? in the sql String
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if(resSet.next()) {
				user = new User();
				user.setId(resSet.getInt("id"));
				user.setUsername(resSet.getString("username"));
				user.setPassword(resSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return user;
	}

	@Override
	public boolean updateUser(User user) {
		
		String sql = "update users set username = ?, password = ? where id = ?;";
		int rowsChanged = -1;
		
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, user.getUsername());
			prepStmt.setString(2, user.getPassword());
			prepStmt.setInt(3, user.getId());
			
			rowsChanged = prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rowsChanged < 1) {
			return false;
		} return true;
	}

	@Override
	public boolean deleteUserById(int id) {
		
		String sql = "delete from users where id = ?;";
		int rowsChanged = -1;
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setInt(1, id);
			
			rowsChanged = prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(rowsChanged < 1) {
			return false;
		} return true;
	}
}