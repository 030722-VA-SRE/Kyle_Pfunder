package com.revature.models;

import java.time.LocalDate;
import java.util.Objects;

public class Item {

	private int id;
	private String itemName;
	private String itemType;
	private LocalDate dateAdded;
	private Boolean isPurchased;
	
	private User userAssigned;
	
	public Item() {
		super();
	}

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Boolean getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(Boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public User getUserAssigned() {
		return userAssigned;
	}

	public void setUserAssigned(User userAssigned) {
		this.userAssigned = userAssigned;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemType=" + itemType + ", dateAdded=" + dateAdded
				+ ", isPurchased=" + isPurchased + ", userAssigned=" + userAssigned + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(dateAdded, id, isPurchased, itemName, itemType, userAssigned);
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
		return Objects.equals(dateAdded, other.dateAdded) && id == other.id
				&& Objects.equals(isPurchased, other.isPurchased) && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemType, other.itemType) && Objects.equals(userAssigned, other.userAssigned);
	}
}