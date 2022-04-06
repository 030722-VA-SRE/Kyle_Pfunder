package com.revature.dtos;

import java.util.Objects;

import com.revature.models.User;


public class UserDTO {

	private int userId;
	private String username;
	
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) {
		this();
		userId = user.getUserId();
		username = user.getUsername();
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return userId == other.userId && Objects.equals(username, other.username);
	}		
}