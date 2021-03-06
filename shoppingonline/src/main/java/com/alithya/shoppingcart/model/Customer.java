package com.alithya.shoppingcart.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Scope("session")
public class Customer extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long customerId;
	
	@NumberFormat(style=Style.NUMBER, pattern="#,###.##")
	private Double customerAvailableAmount;
	
	private Person person;
	
	public Customer() {
	 super();	
	}
	
	public Customer(Long customerId, Double customerAvailableAmount, Long personId, String customerName, String customerSex, LocalDate customerBirthDate, String customerAdress, String customerTelephone, String customerConnectionName, String customerPassword ) {
		
		super(personId, customerName, customerSex, customerBirthDate, customerAdress, customerTelephone, customerConnectionName, customerPassword, 2);
		
		this.customerId = customerId;
		this.customerAvailableAmount = customerAvailableAmount;
	}
	
	public Customer(Long customerId, Double customerAvailableAmount, Person person) {
		super();
		this.customerId = customerId;
		this.customerAvailableAmount = customerAvailableAmount;
		this.person = person;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getCustomerAvailableAmount() {
		return customerAvailableAmount;
	}

	public void setCustomerAvailableAmount(Double customerAvailableAmount) {
		this.customerAvailableAmount = customerAvailableAmount;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("Customer [customerId=" + customerId)
		.append(", customerAvailableAmount=" + customerAvailableAmount)
		.append(", person=" + super.toString())
		.append("]")
		.toString();
		
		//return "Customer [customerId=" + customerId + ", customerAvailableAmount=" + customerAvailableAmount
			//	+ ", person=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, customerAvailableAmount, person);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerAvailableAmount == null) {
			if (other.customerAvailableAmount != null)
				return false;
		} else if (!customerAvailableAmount.equals(other.customerAvailableAmount))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

}
