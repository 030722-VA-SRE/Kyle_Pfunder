package com.revature.repositories;

import com.revature.models.Item;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	//public Item findItemByName(String name);
}