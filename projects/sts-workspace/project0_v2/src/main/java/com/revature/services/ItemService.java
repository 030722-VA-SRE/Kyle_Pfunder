package com.revature.services;

import com.revature.daos.ItemDao;
import com.revature.daos.ItemPostgres;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Item;

import java.util.List;


public class ItemService {

	private ItemDao iDao;
	
	public ItemService() {
		iDao = new ItemPostgres();
	}
	
	public List<Item> getItems() {
		return iDao.getItems();
	}
	
	public Item getById(int id) throws ItemNotFoundException {								// ItemDao
		
		Item item = iDao.getItemById(id);
		
		if(item == null) {
			throw new ItemNotFoundException("Item ID:" + id + "not found.");
		} return item;
	}	
}