package com.revature.repositories;

import com.revature.models.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	public User getUserByUsername(String username);
}