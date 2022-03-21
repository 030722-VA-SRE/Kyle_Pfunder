package com.revature;

import com.revature.services.UserService;
import com.revature.services.ItemService;
import com.revature.util.ConnectionUtil;
import com.revature.models.User;

import io.javalin.Javalin;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;


public class Driver {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create();
		app.start(8080);
			ItemService itemServ = new ItemService();
		app.get("items", (ctx) -> {
			
		});
		
		
		
		
		UserService usr = new UserService();						// create UserService object
		System.out.println("getById:" + usr.getById(2));

		User u = new User();
		u.setUsername("Test");
		u.setPassword("testpass");
		
		usr.addUser(u);
		
		List<User> users = usr.getAll();
		for(User user : users) {
			System.out.println(user);
		}
	}

	public static void basicJdbcSetup() {
		
		
		// because Connection has/can be closed, try with resources closes the resource for us
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			System.out.println(conn.getMetaData().getDriverName());
			
			//c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}