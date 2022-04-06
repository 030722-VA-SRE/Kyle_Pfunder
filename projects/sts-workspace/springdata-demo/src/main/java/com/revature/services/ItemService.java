package com.revature.services;

import com.revature.dtos.UserDTO;
import com.revature.models.Item;
import com.revature.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;


@Service
public class ItemService {

	private ItemRepository itemRep;


	@Autowired
	public ItemService(ItemRepository itemRep) {
		super();
		this.itemRep = itemRep;
	}

	
	public List<Item> getItems() {
		return itemRep.findAll();
	}

	public Item getItemId(int itemId) {
		return itemRep.getById(itemId);
	}

	@Transactional
	public Item createItem(Item item) {
		return itemRep.save(item);
	}

	@Transactional
	public Item updateItem(Item item ) {
		Item update = itemRep.getById(item.getItemId());

		if (item.getItemName() != null && !item.getItemName().equals(update.getItemName())) {
			update.setItemName(item.getItemName());
		}

		if (item.getItemType() != null && !item.getItemType().equals(update.getItemType())) {
			update.setItemType(item.getItemType());
		}
		
		return itemRep.save(update);
	}

	@Transactional
	public void deleteItem(int id) {
		Item item = itemRep.getById(id);
		itemRep.delete(item);
	}
}