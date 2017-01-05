package com.alithya.shoppingcart.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Scope("session")
public class Basket implements Serializable {
	private static final long serialVersionUID = 1L;
	private String basketReference; //customerId + date
	private Map<Long, Item> basketItems;
	private int basketQuantity;
	
	@NumberFormat(style=Style.NUMBER, pattern="#,###.##")
	private Double basketTotalAmount;
	
	private Customer customer;
	private LocalDate basketDate;
	
	public Basket() {}
	
    public String getBasketReference() {
		return basketReference;
	}

	public void setBasketReference(String basketReference) {
		this.basketReference = basketReference;
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
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getBasketDate() {
		return basketDate;
	}

	public void setBasketDate(LocalDate basketDate) {
		this.basketDate = basketDate;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Basket [basketReference=" + basketReference)
				.append(", basketItems=" + basketItems)
				.append(", basketQuantity=" + basketQuantity)
				.append(", basketTotalAmount=" + basketTotalAmount)
				.append(", customer=" + customer)
				.append(", basketDate=" + basketDate)
				.append("]")
				.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(basketReference, basketItems, basketQuantity, basketTotalAmount, customer, basketDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Basket)) {
			return false;
		}
		
		Basket other = (Basket) obj;
		if (basketDate == null) {
			if (other.basketDate != null) {
				return false;
			}
		} else if (!basketDate.equals(other.basketDate)) {
			return false;
		}
		
		if (basketItems == null) {
			if (other.basketItems != null) {
				return false;
			}
		} else if (!basketItems.equals(other.basketItems)) {
			return false;
		}
		
		if (basketQuantity != other.basketQuantity) {
			return false;
		}
		
		if (basketReference == null) {
			if (other.basketReference != null) {
				return false;
			}
		} else if (!basketReference.equals(other.basketReference)) {
			return false;
		}
		
		if (basketTotalAmount == null) {
			if (other.basketTotalAmount != null) {
				return false;
			}
		} else if (!basketTotalAmount.equals(other.basketTotalAmount)) {
			return false;
		}
		
		if (customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!customer.equals(other.customer)) {
			return false;
		}
		
		return true;
	}
	
}
