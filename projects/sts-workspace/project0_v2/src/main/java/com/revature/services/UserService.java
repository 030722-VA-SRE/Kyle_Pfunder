package com.revature.services;

import com.revature.daos.UserPostgres;
import com.revature.daos.UserDao;
import com.revature.models.User;

import java.util.List;


public class UserService {
	
	private UserDao uDao = new UserPostgres();
	
	public User getById(int id) {
		User usr = uDao.getUserById(id);
		
		/*- add business logic
		 * 		- id u = null,  u doesn't exist in the database, throw an exception
		 * 			-  UserNotFoundException
		 * 
		 */
		
		return usr;
	}
	
	public List<User> getAll() {
		// Can add some logic here,  if list is empty throw an exception
		return uDao.getUsers();
	}
	
	public boolean addUser(User user) {
		// validation of user object, add business logic
		int newId = uDao.createUser(user);
		
		if(newId == -1) {
			// throw an exception
			return false;
		} return true;
	}
}