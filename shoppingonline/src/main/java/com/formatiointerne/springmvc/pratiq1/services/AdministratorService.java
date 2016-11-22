package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;

import com.formatiointerne.springmvc.pratiq1.datamodels.Administrator;

public interface AdministratorService {
	Administrator createNewAdmin(String name, String sex, LocalDate birthDate, String adress, String telephone, String connexionname, String motdepasse, String matricule, LocalDate dateBeginning, LocalDate dateEnding);
	boolean modifyIdentityAdmin(String matricule, String name, String sex, LocalDate birthDate); 
	boolean modifyAdressAdmin(String matricule, String adress, String telephone); 
	boolean modifyConnexionIdentifiants (String matricule, String connexionname, String motdepasse); 
	boolean modifyCaracteristicClient (String matricule, LocalDate dateBeginning, LocalDate dateEnding); 
	Administrator getAdminByMatricule(String matricule);
	boolean removeClient(String matricule);
}
