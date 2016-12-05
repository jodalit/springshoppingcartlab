package com.formatiointerne.springmvc.pratiq1.datamodels;

import java.time.LocalDate;
import java.util.Objects;

public class Administrator extends Person {
	private String adminMatricule;
	private LocalDate dateBeginningAdmin;
	private LocalDate dateEndingAdmin;
	
	public Administrator() {
		super();
	}
	
	public Administrator(String adminMatricule, LocalDate dateBeginningAdmin, LocalDate dateEndingAdmin) {
		super();
		this.adminMatricule = adminMatricule;
		this.dateBeginningAdmin = dateBeginningAdmin;
		this.dateEndingAdmin = dateEndingAdmin;
	}

	public String getAdminMatricule() {
		return adminMatricule;
	}
	
	public void setAdminMatricule(String adminMatricule) {
		this.adminMatricule = adminMatricule;
	}
	
	public LocalDate getDateBeginningAdmin() {
		return dateBeginningAdmin;
	}
	
	public void setDateBeginningAdmin(LocalDate dateBeginningAdmin) {
		this.dateBeginningAdmin = dateBeginningAdmin;
	}
	
	public LocalDate getDateEndingAdmin() {
		return dateEndingAdmin;
	}
	
	public void setDateEndingAdmin(LocalDate dateEndingAdmin) {
		this.dateEndingAdmin = dateEndingAdmin;
	}

	@Override
	public String toString() {
		return "Administrator [\n" + super.toString() + ",\n adminMatricule=" + adminMatricule + ", dateBeginningAdmin=" + dateBeginningAdmin
				+ ", dateEndingAdmin=" + dateEndingAdmin + "\n]";
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result+ Objects.hash(adminMatricule, dateBeginningAdmin, dateEndingAdmin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		if (adminMatricule == null) {
			if (other.adminMatricule != null)
				return false;
		} else if (!adminMatricule.equals(other.adminMatricule))
			return false;
		if (dateBeginningAdmin == null) {
			if (other.dateBeginningAdmin != null)
				return false;
		} else if (!dateBeginningAdmin.equals(other.dateBeginningAdmin))
			return false;
		if (dateEndingAdmin == null) {
			if (other.dateEndingAdmin != null)
				return false;
		} else if (!dateEndingAdmin.equals(other.dateEndingAdmin))
			return false;
		return true;
	}
	
	
	
	
}
