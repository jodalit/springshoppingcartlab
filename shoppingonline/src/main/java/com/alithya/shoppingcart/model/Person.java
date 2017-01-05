package com.alithya.shoppingcart.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long personId;
	private String personName;
	private String personSex;
	private LocalDate personBirthDate;
	private String personAdress;
	private String personTelephone;
	private String personConnectionName;
	private String personPassword;
	private int profile; //1 for admin; 2 for client; 0 for super admin 
	
	public Person() {
	}

	public Person(Long personId, String personName, String personSex, LocalDate personBirthDate, String personAdress,
			String personTelephone) {
		this.personId = personId;
		this.personName = personName;
		this.personSex = personSex;
		this.personBirthDate = personBirthDate;
		this.personAdress = personAdress;
		this.personTelephone = personTelephone;
	}
	
	public Person(Long personId, String personName, String personSex, LocalDate personBirthDate, String personAdress,
			String personTelephone, String personConnectionName, String personPassword, int profile) {
		this.personId = personId;
		this.personName = personName;
		this.personSex = personSex;
		this.personBirthDate = personBirthDate;
		this.personAdress = personAdress;
		this.personTelephone = personTelephone;
		this.personConnectionName = personConnectionName;
		this.personPassword = personPassword;
		this.profile= profile;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSex() {
		return personSex;
	}

	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}

	public LocalDate getPersonBirthDate() {
		return personBirthDate;
	}

	public void setPersonBirthDate(LocalDate personBirthDate) {
		this.personBirthDate = personBirthDate;
	}

	public String getPersonAdress() {
		return personAdress;
	}

	public void setPersonAdress(String personAdress) {
		this.personAdress = personAdress;
	}

	public String getPersonTelephone() {
		return personTelephone;
	}

	public void setPersonTelephone(String personTelephone) {
		this.personTelephone = personTelephone;
	}
	
	public String getPersonConnectionName() {
		return personConnectionName;
	}

	public void setPersonConnectionName(String personConnectionName) {
		this.personConnectionName = personConnectionName;
	}

	public String getPersonPassword() {
		return personPassword;
	}

	public void setPersonPassword(String personPassword) {
		this.personPassword = personPassword;
	}
	
	public int getProfile() {
		return profile;
	}

	public void setProfile(int profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Person [personId= " + personId)
				.append(", personName= " + personName)
				.append(", personSex= " + personSex)
				.append(", personBirthDate= " + personBirthDate)
				.append(", personAdress= " + personAdress)
				.append(", personTelephone= " + personTelephone)
				.append(", connexionname= " + personConnectionName)
				.append(", profil = " + profile)
				.append("]")
				.toString();
		
		/*return "Person [\npersonId= " + personId + ", personName= " + personName + ", personSex= " + personSex
				+ ", personBirthDate= " + personBirthDate + ", personAdress= " + personAdress + ", personTelephone= "
				+ personTelephone + ", connexionname= " + personConnectionName + ", profil = " + profile + "\n]";*/
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personConnectionName == null) {
			if (other.personConnectionName != null)
				return false;
		} else if (!personConnectionName.equals(other.personConnectionName))
			return false;
		if (personPassword == null) {
			if (other.personPassword != null)
				return false;
		} else if (!personPassword.equals(other.personPassword))
			return false;
		if (personAdress == null) {
			if (other.personAdress != null)
				return false;
		} else if (!personAdress.equals(other.personAdress))
			return false;
		if (personBirthDate == null) {
			if (other.personBirthDate != null)
				return false;
		} else if (!personBirthDate.equals(other.personBirthDate))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		if (personSex == null) {
			if (other.personSex != null)
				return false;
		} else if (!personSex.equals(other.personSex))
			return false;
		if (personTelephone == null) {
			if (other.personTelephone != null)
				return false;
		} else if (!personTelephone.equals(other.personTelephone))
			return false;
		if (profile != other.profile)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, personName, personSex, personBirthDate, personAdress, personTelephone, personConnectionName, personPassword, profile);
	}
	
	

	
	
}
