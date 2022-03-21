package com.revature.daos;

import com.revature.models.User;

import java.util.List;


public interface UserDao {
	
	public int createUser(User user);
	public List<User> getUsers();
	public User getUserById(int id);
	public User getUserByName(String username);
	public boolean updateUser(User user);
	public boolean deleteUserById(int id);
}
