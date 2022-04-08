package com.revature.controllers;

import com.revature.models.Item;
import com.revature.dtos.ItemDTO;
import com.revature.services.AuthService;
import com.revature.services.ItemService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/items")
public class ItemController {
	
	private ItemService itemServ;
	//private AuthService authServ;
	
	
	@Autowired
	public ItemController(ItemService itemServ, AuthService authServ) {
		super();
		this.itemServ = itemServ;
		//this.authServ = authServ;
	}
	

	
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getAllItems(@RequestHeader(value = "Authorization", required = false) String token) {
		MDC.put("requestId", UUID.randomUUID().toString());
		//authServ.verify(token);
		return new ResponseEntity<>(itemServ.getItems(), HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ItemDTO> getItemId(@PathVariable("itemId") int itemId) {
		return new ResponseEntity<>(itemServ.getItemById(itemId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> postItem(@RequestBody Item item) {
		ItemDTO itemDTO = itemServ.createItem(item);
		return new ResponseEntity<>("Item: "+itemDTO.getItemName()+" [ADDED]", HttpStatus.CREATED);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<ItemDTO> updateItem(@PathVariable("itemId") int itemId, @RequestBody Item item) {
		item.setItemId(itemId);
		return new ResponseEntity<>(itemServ.updateItem(item), HttpStatus.OK);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable("itemId") int itemId) {
		itemServ.deleteItem(itemId);
		return new ResponseEntity<>("Item: "+itemId+" [DELETED]", HttpStatus.OK);
	}	
}