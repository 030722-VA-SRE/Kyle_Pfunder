package com.revature.daos;

import com.revature.models.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface ItemDao {

	public List<Item> getItems();
	public List<Item> getItemsByUser(int userId);					
	public List<Item> getItemsByType(String itemType);				 			 
	public List<Item> getItemsIfPurchased(boolean isPurchased);		 
	public int addItem(Item item);		
	public Item getItemByName(String itemName);
	public Item getItemById(int itemId);								 
	public boolean updateItem(Item item);							
	public boolean deleteItemById(int itemId);											
}