package com.alithya.shoppingcart.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Basket implements Serializable {
	private Map<Long, Item> basketItems;
	private int basketQuantity;
	private Double basketTotalAmount;
	
	public Basket() {
		Map<Long,Item> basketItems = new HashMap<>(); 
		
	}
	
    public Map<Long, Item> getBasketItems() {
		
		return basketItems;
	}

	public void setBasketItems(Map<Long, Item> basketItems) {
		this.basketItems = basketItems;
	}

	public int getBasketQuantity() {
		return basketQuantity;
	}

	public void setBasketQuantity(int basketQuantity) {
		this.basketQuantity = basketQuantity;
	}

	public Double getBasketTotalAmount() {
		return basketTotalAmount;
	}

	public void setBasketTotalAmount(Double basketTotalAmount) {
		this.basketTotalAmount = basketTotalAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(basketItems, basketQuantity, basketTotalAmount);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Basket other = (Basket) obj;
		if (basketItems == null) {
			if (other.basketItems != null)
				return false;
		} else if (!basketItems.equals(other.basketItems))
			return false;
		if (basketQuantity != other.basketQuantity)
			return false;
		if (basketTotalAmount == null) {
			if (other.basketTotalAmount != null)
				return false;
		} else if (!basketTotalAmount.equals(other.basketTotalAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Basket [basketItems=" + basketItems + ", basketQuantity=" + basketQuantity
				+ ", basketTotalAmount=" + basketTotalAmount + "]";
	}

	
	
}
