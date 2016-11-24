package com.formatiointerne.springmvc.pratiq1.datamodels;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
	private Long personId;
	private String personName;
	private String personSex;
	private LocalDate personBirthDate;
	private String personAdress;
	private String personTelephone;
	private String connexionname;
	private String password;
	private int profile; //1 for admin and 2 for client 
	
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
			String personTelephone, String connexionName, String password, int profile) {
		this.personId = personId;
		this.personName = personName;
		this.personSex = personSex;
		this.personBirthDate = personBirthDate;
		this.personAdress = personAdress;
		this.personTelephone = personTelephone;
		this.connexionname = connexionName;
		this.password = password;
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
	
	public String getConnexionname() {
		return connexionname;
	}

	public void setConnexionname(String connexionname) {
		this.connexionname = connexionname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getProfile() {
		return profile;
	}

	public void setProfile(int profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Person [\npersonId= " + personId + ", personName= " + personName + ", personSex= " + personSex
				+ ", personBirthDate= " + personBirthDate + ", personAdress= " + personAdress + ", personTelephone= "
				+ personTelephone + ", connexionname= " + connexionname + ", profil = " + profile + "\n]";
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
		if (connexionname == null) {
			if (other.connexionname != null)
				return false;
		} else if (!connexionname.equals(other.connexionname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return Objects.hash(personId, personName, personSex, personBirthDate, personAdress, personTelephone, connexionname, password, profile);
	}
	
	

	
	
}
