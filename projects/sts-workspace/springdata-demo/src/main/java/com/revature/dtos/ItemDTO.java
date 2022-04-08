package com.revature.dtos;

import java.util.Objects;

import com.revature.models.Item;


public class ItemDTO {
	
	private int itemId;
	private String itemName;
	private String itemType;
	private UserDTO userAssigned;
	
	
	public ItemDTO() {
		super();
	}

	public ItemDTO(Item item) {
		super();
		this.itemId = item.getItemId();
		this.itemName = item.getItemName();
		this.itemType = item.getItemType();
		this.userAssigned = new UserDTO(item.getUserAssigned());
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public UserDTO getUser() {
		return userAssigned;
	}

	public void setUser(UserDTO user) {
		this.userAssigned = user;
	}

	
	@Override
	public String toString() {
		return "itemDTO [itemId=" + itemId + ", itemName=" + itemName + ", itemType=" + itemType + ", userAssigned=" + userAssigned
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, itemName, itemType, userAssigned);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDTO other = (ItemDTO) obj;
		return itemId == other.itemId && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemType, other.itemType) && Objects.equals(userAssigned, other.userAssigned);
	}	
}