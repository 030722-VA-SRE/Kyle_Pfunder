package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="items")
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemId;
	@Column(nullable = false)
	private String itemName;
	@Column(nullable = false)
	private String itemType;

	@ManyToOne
	@JoinColumn(nullable = false, name = "user_assigned")
	private User userAssigned;


	public Item() {
		super();
	}

	public Item(int itemId, String itemName, String itemType, User userAssigned) {
		this();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemType = itemType;
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

	public User getUserAssigned() {
		return userAssigned;
	}

	public void setUserAssigned(User userAssigned) {
		this.userAssigned = userAssigned;
	}


	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemType=" + itemType + ", userAssigned="
				+ userAssigned + "]";
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
		Item other = (Item) obj;
		return itemId == other.itemId && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemType, other.itemType) && Objects.equals(userAssigned, other.userAssigned);
	}
}