package com.revature.models;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Item {

	private int itemId;
	private String itemName;
	private String itemType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	
	private Boolean isPurchased;
	private int userAssigned;
	
	public Item() {
		super();
	}
	
	public Item(String itemName, String itemType, boolean isPurchased, int userAssigned) {
		super();
		
		this.itemName = itemName;
		this.itemType = itemType;
		this.isPurchased = isPurchased;
		this.userAssigned = userAssigned;
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

	public Boolean getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(Boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public int getUserAssigned() {
		return userAssigned;
	}

	public void setUserAssigned(int userAssigned) {
		this.userAssigned = userAssigned;
	}


	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemType=" + itemType + ", isPurchased="
				+ isPurchased + ", userAssigned=" + userAssigned + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isPurchased, itemId, itemName, itemType, userAssigned);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(isPurchased, other.isPurchased) && itemId == other.itemId
				&& Objects.equals(itemName, other.itemName) && Objects.equals(itemType, other.itemType)
				&& userAssigned == other.userAssigned;
	}
}