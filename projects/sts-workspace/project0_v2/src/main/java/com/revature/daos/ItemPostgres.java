package com.revature.daos;

import com.revature.models.Item;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ItemPostgres implements ItemDao{

	@Override
	public List<Item> getItems() {
		String sql = "select gl.id, gl.item_name, gl.item_type, gl.date_added, gl.is_purchased\r\n"
				   + "from grocery_list gl"
				   + "join users u on gl.user_id = u.id;";
		
		List<Item> items = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnectionFromEnv()){
			Statement stmt = conn.createStatement();
			ResultSet resSet = stmt.executeQuery(sql);
			
			while(resSet.next()) {
				Item item = new Item();
				item.setId(resSet.getInt("id"));
				item.setItemName("item_name");
				item.setItemType(resSet.getString("item_type"));
				item.setDateAdded(resSet.getDate("date_added").toLocalDate());
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
						
				User user = new User();
				user.setId(resSet.getInt("user_id"));
				item.setUserAssigned(user);
				
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				item.setId(resSet.getInt("id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setDateAdded(resSet.getDate("date_added").toLocalDate());
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
						
				User user = new User();
				user.setId(resSet.getInt("user_id"));
				item.setUserAssigned(user);
				
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return items;
	}

	@Override
	public List<Item> getItemsByType(String type) {
		String sql = "select from grocery_list where type = ?;";
		List<Item> items = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, type);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			while (resSet.next()) {
				Item item = new Item();
				item.setId(resSet.getInt("id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setDateAdded(resSet.getDate("date_added").toLocalDate());
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				
				User user = new User();
				user.setId(resSet.getInt("user_id"));
				item.setUserAssigned(user);
				
				items.add(item);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} return items;
	}

	@Override
	public List<Item> getItemsByDate(LocalDate dateAdded) {
		String sql = "select * from grocery_list where date_added = ?;";
		List<Item> items = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setDate(1, Date.valueOf(dateAdded));
			
			ResultSet resSet = prepStmt.executeQuery();
			
			while (resSet.next()) {
				Item item = new Item();
				item.setId(resSet.getInt("id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setDateAdded(resSet.getDate("date_added").toLocalDate());
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				
				User user = new User();
				user.setId(resSet.getInt("id"));
				item.setUserAssigned(user);
				
				items.add(item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return items;
	}

	@Override
	public List<Item> getItemsIfPurchsed(boolean isPurchased) {
		String sql = "select * from grocery_list where is_purchased = ?;";
		List<Item> items = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setBoolean(1, isPurchased);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			while (resSet.next()) {
				Item item = new Item();
				item.setId(resSet.getInt("id"));
				item.setItemName(resSet.getString("item_name"));
				item.setItemType(resSet.getString("item_type"));
				item.setDateAdded(resSet.getDate("date_added").toLocalDate());
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				
				User user = new User();
				user.setId(resSet.getInt("user_id"));
				item.setUserAssigned(user);
				
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return items;
	}

	@Override
	public int addItem(Item item) {
		String sql = "insert into grocery_list(item_name, item_type, date_added, user_id) values(?,?,?,?);";
		int createId = -1;
				
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);

			prepStmt.setString(1, item.getItemName());
			prepStmt.setString(2, item.getItemType());
			prepStmt.setDate(3, Date.valueOf(item.getDateAdded()));
			prepStmt.setBoolean(4, item.getIsPurchased());
			prepStmt.setInt(5, item.getUserAssigned().getId());
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if (resSet.next()) {
				createId = resSet.getInt("id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return createId;
	}

	@Override
	public Item getItemById(int id) {
		String sql = "select * from grocery_list where id = ?;";
		Item item = new Item();
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setInt(1, id);
			
			ResultSet resSet = prepStmt.executeQuery();
			
			if(resSet.next()) {
				item = new Item();
				item.setId(resSet.getInt("id"));
				item.setItemName("item_name");
				item.setItemType(resSet.getString("item_type"));
				item.setDateAdded(resSet.getDate("date_added").toLocalDate());
				item.setIsPurchased(resSet.getBoolean("is_purchased"));
				
				User user = new User();
				user.setId(resSet.getInt("user_id"));
				item.setUserAssigned(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return item;
	}

	@Override
	public boolean updateItem(Item item) {
		String sql = "update grocery_list set item_name = ?, item_type = ?, date_added = ? is_purchased = ? where id = ?;";
		int rowsChanged = -1;
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, item.getItemName());
			prepStmt.setString(2, item.getItemType());
			prepStmt.setDate(3, Date.valueOf(item.getDateAdded()));
			prepStmt.setBoolean(4, item.getIsPurchased());
			prepStmt.setInt(5, item.getUserAssigned().getId());
			
			rowsChanged = prepStmt.executeUpdate();				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} if (rowsChanged < 1) {
			return false;
		} return true;
	}

	@Override
	public boolean deleteItemById(int id) {
		String sql = "delete from grocery_list where id = ?;";
		int rowsChanged = -1;
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setInt(1, id);
			
			rowsChanged = prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} if (rowsChanged < 1) {
			return false;
		} return true;
	}

	@Override
	public boolean deleteItemByName(String itemName) {
		String sql = "delete from grocery_list where item_name = ?;";
		int rowsChanged = -1;
		
		try (Connection conn = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			
			prepStmt.setString(1, itemName);
			
			rowsChanged = prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} if (rowsChanged < 1) {
			return false;
		} return true;
	}
}