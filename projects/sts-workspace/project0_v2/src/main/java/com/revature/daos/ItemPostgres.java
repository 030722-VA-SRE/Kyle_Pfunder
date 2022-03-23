package com.revature.daos;

import com.revature.models.Item;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ItemPostgres implements ItemDao{
	
	private static Logger log = LogManager.getLogger(ItemDao.class);
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	private static LocalDateTime now = LocalDateTime.now();

	@Override
	public List<Item> getItems() {
		String sql = "select gl.item_id, gl.item_name, gl.item_type, gl.is_purchased, gl.user_id from grocery_list gl";
		
		List<Item> items = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			Statement stmt = conn.createStatement();
			ResultSet resSet = stmt.executeQuery(sql);
			
			while(resSet.next()) {
				Item item = new Item();
				item.setItemId(resSet.getInt("item_id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				item.setUserAssigned(resSet.getInt("user_id"));				
				
				items.add(item);
				log.info(dtf.format(now) + items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return items;
	}

	@Override
	public List<Item> getItemsByUser(int userId) {
		String sql = "select * from grocery_list where user_id = ?;";
		List<Item> items = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setInt(1, userId);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			while (resSet.next()) {
				Item item = new Item();
				item.setItemId(resSet.getInt("item_id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				item.setUserAssigned(resSet.getInt("user_id"));		
				
				items.add(item);
				log.info(dtf.format(now) + items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return items;
	}

	public Item getItemByName(String itemName) {
		String sql = "select from grocery_list where item_name = ?;";
		Item item = new Item();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, itemName);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if(resSet.next()) {
				item = new Item();
				item.setItemId(resSet.getInt("item_id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				item.setUserAssigned(resSet.getInt("user_id"));

			}
		}catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return item;
	}
	
	
	@Override
	public List<Item> getItemsByType(String itemType) {
		String sql = "select from grocery_list where type = ?;";
		List<Item> items = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, itemType);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			while (resSet.next()) {
				Item item = new Item();
				item.setItemId(resSet.getInt("item_id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				item.setUserAssigned(resSet.getInt("user_id"));
				
				items.add(item);
				log.info(dtf.format(now) + items);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return items;
	}


	@Override
	public List<Item> getItemsIfPurchased(boolean isPurchased) {
		String sql = "select * from grocery_list where is_purchased = ?;";
		List<Item> items = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setBoolean(1, isPurchased);
						
			ResultSet resSet = prepStmt.executeQuery();
			
			while (resSet.next()) {
				Item item = new Item();
				item.setItemId(resSet.getInt("item_id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				item.setUserAssigned(resSet.getInt("user_id"));
								
				items.add(item);
				log.info(dtf.format(now) + items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return items;
	}

	@Override
	public int addItem(Item item) {
		String sql = "insert into grocery_list(item_name, item_type, user_id) values(?,?,?) returning item_id;";
		int createId = -1;
				
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);

			prepStmt.setString(1, item.getItemName());
			prepStmt.setString(2, item.getItemType());
			prepStmt.setInt(3, item.getUserAssigned());
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if (resSet.next()) {
				createId = resSet.getInt("item_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return createId;
	}

	@Override
	public Item getItemById(int id) {
		String sql = "select * from grocery_list where item_id = ?;";
		Item item = null;
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			System.out.println("testing");
			prepStmt.setInt(1, id);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if(resSet.next()) {
				item = new Item();
				item.setItemId(resSet.getInt("item_id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				item.setUserAssigned(resSet.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		} return item;
	}

	@Override
	public boolean updateItem(Item item) {
		String sql = "update grocery_list set item_name = ?, item_type = ?, is_purchased = ?, user_id = ? where item_id = ?;";
				
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			System.out.println(item.getItemId() + item.getItemName() + item.getItemType() + item.getIsPurchased() + item.getUserAssigned());
			
			prepStmt.setString(1, item.getItemName());
			prepStmt.setString(2, item.getItemType());
			prepStmt.setBoolean(3, item.getIsPurchased());
			prepStmt.setInt(4, item.getUserAssigned());
			prepStmt.setInt(5, item.getItemId());
			
			int rowsChanged = prepStmt.executeUpdate();
			
			if (rowsChanged > 0) {
			return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
			}
		  return true;
		}

	
	@Override
	public boolean deleteItemById(int id) {
		String sql = "delete from grocery_list where item_id = ?;";
		
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setInt(1, id);
			
			int rowsChanged = prepStmt.executeUpdate();
			if (rowsChanged < 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(dtf.format(now) + e.getMessage());
		}  return true;
	}
}