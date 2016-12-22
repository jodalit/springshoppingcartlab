package com.alithya.shoppingcart.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.MultipartFile;

//@XmlRootElement
@Scope("session")
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long itemId;
	private String itemName;
	private String description;
	private Double price;
	private LocalDate expireDate;
	private MultipartFile itemImage;
	
	public Item() {
	}

	public Item(Long itemId, String itemName, String description, Double price) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
	}

	public Item(Long itemId, String itemName, String description, Double price, LocalDate expireDate) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.expireDate = expireDate;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	
	public MultipartFile getItemImage() {
		return itemImage;
	}

	public void setItemImage(MultipartFile itemImage) {
		this.itemImage = itemImage;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", price=" + price +", expireDate=" + expireDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, itemName, description, price, expireDate);
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expireDate == null) {
			if (other.expireDate != null)
				return false;
		} else if (!expireDate.equals(other.expireDate))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
}
