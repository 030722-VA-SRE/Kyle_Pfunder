package com.revature.services;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.dtos.ItemDTO;
import com.revature.models.Item;
import com.revature.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemService {

	private ItemRepository itemRepo;


	@Autowired
	public ItemService(ItemRepository itemRepo) {
		super();
		this.itemRepo= itemRepo;
	}

	
	public List<ItemDTO> getItems() {
		List<Item> items = itemRepo.findAll(Sort.by(Sort.Direction.ASC,"itemId"));
		List<ItemDTO> itemsDTO = items.stream()
			.map((item) -> new ItemDTO(item))
			.collect(Collectors.toList());
		return itemsDTO;
	}

	public ItemDTO getItemById(int itemId) {
		Item item = itemRepo.findById(itemId).orElseThrow(ItemNotFoundException::new); 
		return new ItemDTO(item);
	}

	@Transactional
	public ItemDTO createItem(Item item) {
		return new ItemDTO(itemRepo.save(item));
	}

	@Transactional
	public ItemDTO updateItem(Item item ) {
		Item update = itemRepo.getById(item.getItemId());

		if (item.getItemName() != null && !item.getItemName().equals(update.getItemName())) {
			update.setItemName(item.getItemName());
		}

		if (item.getItemType() != null && !item.getItemType().equals(update.getItemType())) {
			update.setItemType(item.getItemType());
		}
		
		return new ItemDTO(itemRepo.save(update));
	}

	@Transactional
	public void deleteItem(int id) {
		Item item = itemRepo.getById(id);
		itemRepo.delete(item);
	}
}