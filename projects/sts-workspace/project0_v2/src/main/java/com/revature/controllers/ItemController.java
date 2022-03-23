package com.revature.controllers;

import com.revature.services.ItemService;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;




public class ItemController {
	
	private static ItemService itemServ = new ItemService();
	private static Logger log = LogManager.getRootLogger();
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	private static LocalDateTime now = LocalDateTime.now();
	
	
	public static void getItems(Context ctx) {
		String itemName = ctx.queryParam("itemName");
		String itemType = ctx.queryParam("itemType");
		String isPurchased = ctx.queryParam("isPurchased");
		String userId = ctx.queryParam("userId");
		
		try {
		if (itemName == null & itemType == null & isPurchased == null & userId == null) {
			ctx.json(itemServ.getItems());
			ctx.status(200);	
			} 
			
		else if (isPurchased != null) {
			boolean isPurchasedValue = Boolean.parseBoolean(ctx.pathParam("isPurchased"));
			System.out.println(isPurchasedValue);
			
			List<Item> items = itemServ.getItemsIfPurchased(isPurchasedValue);
			ctx.json(items);
			}
				
			}
			catch (ItemNotFoundException e) {
				e.printStackTrace();
				ctx.status(HttpStatus.NOT_FOUND_404);
				log.error(dtf.format(now) + e);
			}
		}							

		
	
	public static void addItems(Context ctx) {
		Item item = ctx.bodyAsClass(Item.class); 
		
		if (itemServ.addItem(item) > 0) {
			ctx.status(HttpStatus.CREATED_201);
			ctx.result("item added");
			log.info(dtf.format(now) + item + "added");
		}
		else {
			ctx.status(HttpStatus.BAD_REQUEST_400);
			ctx.result("unable to add item");
			log.error(dtf.format(now) + "unable to add item: " + item);
		}
	}
	
	
	public static void getItemById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("itemId"));
		try {
			ctx.json(itemServ.getItemById(id));
			ctx.status(HttpStatus.OK_200);
			log.info(dtf.format(now) + "item id: " + id + " found");
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			ctx.status(HttpStatus.NOT_FOUND_404);
			log.error(dtf.format(now) + e);
		}
	}
		
	
	public static void updateItem(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("itemId"));
		Item item = ctx.bodyAsClass(Item.class);
				
		item.setItemId(id);
		
		try {
			if (itemServ.updateItem(item)) {
				ctx.status(HttpStatus.OK_200);
				ctx.result("item updated");
				log.info(dtf.format(now) + "item updated");
			}
		} catch (ItemNotFoundException e) {
			log.error(dtf.format(now) + e);
			e.printStackTrace();
		}
	}	


	public static void deleteItem(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("itemId"));
		try {
			if (itemServ.deleteItemById(id)) {
				ctx.status(HttpStatus.OK_200);
				ctx.result("deleted item with id: " + id);
				log.info("item id: " + id + " deleted");
				}
			else {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("no item found with id: " + id);
				log.error(dtf.format(now) + "item id: " + id + " not found");
			}
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		}
	}
}