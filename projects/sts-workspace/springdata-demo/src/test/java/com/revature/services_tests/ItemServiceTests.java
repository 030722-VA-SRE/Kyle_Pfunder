package com.revature.services_tests;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.repositories.ItemRepository;
import com.revature.services.ItemService;
import com.revature.dtos.ItemDTO;
import com.revature.models.Item;
import com.revature.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {
	
	static List<ItemDTO> itemDTO = new ArrayList<>();
	static List<Item> items = new ArrayList<>();
	static ItemRepository itemRepo; 
	static ItemService itemServ; 
	static Item item;
	static User user;
	

	
	
	@BeforeAll
	public static void test() {
		
		itemRepo = mock(ItemRepository.class);
		itemServ = new ItemService(itemRepo); 
		user = new User(1, "Kyle", "Password1","kyle705@revature.net");
		item = new Item(1, "testItem", "testItemType", user);
		items.add(item);
		itemDTO.add(new ItemDTO(item));
		
	}
		@Test
		public void getAllItemsTest() {
			when(itemRepo.findAll()).thenReturn(items);
			assertEquals(itemDTO, itemServ.getItems());
			assertEquals(itemDTO.size(), itemServ.getItems().size());
		}
		
		@Test
		public void getItemByIdTest() throws ItemNotFoundException {
			when(itemRepo.findById(1)).thenReturn(Optional.of(item)); 
			assertEquals(new ItemDTO(item), itemServ.getItemById(1));
		}
		
		@Test 
		public void createItemTest() {
			when(itemRepo.save(item)).thenReturn(item); 
			assertEquals(new ItemDTO(item), itemServ.createItem(item)); 
		}
		
		@Test
		public void updateItemTest() throws ItemNotFoundException {
			User user2 = new User(2, "test2", "password2", "test2@test.test");
			User user3 =new User(3, "test2", "password2", "test2@test.test");
			Item item2 = new Item(2, "test2", "testItemType2", user2);
			Item item3 = new Item(2, "test3", "testItemType3", user3);
			
			
			when(itemRepo.findById(1)).thenReturn(Optional.of(item)); 
			when(itemRepo.save(item)).thenReturn(item); 
			assertEquals(item, itemServ.updateItem(item));
		}
		
		@Test 
		public void deleteItemTest() throws ItemNotFoundException {
			when(itemRepo.findById(anyInt())).thenReturn(Optional.of(item));
			doNothing().when(itemRepo).delete(any(Item.class));
			itemServ.deleteItem(1);
			
			//assertEquals(true, itemServ.deleteItem(1));
		}
	}

