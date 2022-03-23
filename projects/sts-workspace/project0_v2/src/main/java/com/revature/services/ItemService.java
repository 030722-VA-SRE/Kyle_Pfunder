package com.revature.services;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.daos.ItemPostgres;
import com.revature.daos.ItemDao;
import com.revature.models.Item;

import java.util.List;


public class ItemService {

	private ItemDao iDao;
	
	public ItemService() {
		iDao = new ItemPostgres();
	}
	
	public List<Item> getItems() throws ItemNotFoundException {
		return iDao.getItems();
	}
		
	public List<Item> getItemsByUser(int userId) {
		return getItemsByUser(userId);
	}
	
	public List<Item> getItemsByType(String itemType) {
		return iDao.getItemsByType(itemType);
	}
		
	public List<Item> getItemsIfPurchased(boolean isPurchased) throws ItemNotFoundException {
		return iDao.getItemsIfPurchased(isPurchased);
	}
	
	public int addItem(Item item) {
		return iDao.addItem(item);
	}
	
	public Item getItemById(int id) throws ItemNotFoundException {								// ItemDao
		
		Item item = iDao.getItemById(id);
		
		if(item == null) {
			throw new ItemNotFoundException("item" + id + "not found");
		} return item;
	}
	

	public boolean updateItem(Item item) throws ItemNotFoundException {
		Item updates = iDao.getItemById(item.getItemId());
		
		if (item.getItemName() != null && item.getItemName() != updates.getItemName()) {
			updates.setItemName(item.getItemName());
		}
		if (item.getItemType() != null && item.getItemType() != updates.getItemType()) {
			updates.setItemType(item.getItemType());
		}
		if (item.getIsPurchased() != updates.getIsPurchased()) {
			updates.setIsPurchased(item.getIsPurchased());
		}
		if (item.getUserAssigned() != updates.getUserAssigned()) {
			updates.setUserAssigned(item.getUserAssigned());
		}
		return iDao.updateItem(updates);
	}
		
	
	public boolean deleteItemById(int id) throws ItemNotFoundException {
		return iDao.deleteItemById(id);
	}
}