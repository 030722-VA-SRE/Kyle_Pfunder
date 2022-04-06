package com.revature.controllers;

import com.revature.models.Item;
import com.revature.services.ItemService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/items")
public class ItemController {
	
	private ItemService itemServ;
	
	
	@Autowired
	public ItemController(ItemService is) {
		super();
		this.itemServ = is;
	}
	

	// GET /items
	@GetMapping
	public ResponseEntity<List<Item>> getAllItems() {
		
		return new ResponseEntity<>(itemServ.getItems(), HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<Item> getItemId(@PathVariable("itemId") int itemId) {
		return new ResponseEntity<>(itemServ.getItemId(itemId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Item> postItem(@RequestBody Item item) {
		Item newItem = itemServ.createItem(item);
		return new ResponseEntity<>(newItem, HttpStatus.CREATED);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable("itemId") int itemId, @RequestBody Item item) {
		item.setItemId(itemId);
		return new ResponseEntity<>(itemServ.updateItem(item), HttpStatus.OK);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable("itemId") int itemId) {
		itemServ.deleteItem(itemId);
		return new ResponseEntity<>("Item deleted.", HttpStatus.OK);
	}	
}