package com.revature.daos;

import com.revature.models.Item;

import java.time.LocalDate;
import java.util.List;


public interface ItemDao {

	public List<Item> getItems();
	public List<Item> getItemsByUser(int userId);
	public List<Item> getItemsByType(String type);
	public List<Item> getItemsByDate(LocalDate dateAdded);
	public List<Item> getItemsIfPurchsed(boolean isPurchased);
	public int addItem(Item item);
	public Item getItemById(int id);
	public boolean updateItem(Item item);
	public boolean deleteItemById(int id);
	public boolean deleteItemByName(String itemName);
	
}