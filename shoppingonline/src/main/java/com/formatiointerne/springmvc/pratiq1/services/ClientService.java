package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.List;

import com.formatiointerne.springmvc.pratiq1.datamodels.Client;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;

public interface ClientService {
	Client createNewClient(String name, String sex, LocalDate birthDate, String adress, String telephone, String connexionname, String motdepasse, String clientType, boolean isLocalClient);
	boolean modifyIdentityClient(Long clientId, String name, String sex, LocalDate birthDate); 
	boolean modifyAdressClient(Long clientId, String adress, String telephone); 
	boolean modifyConnexionIdentifiants (Long clientId, String connexionname, String motdepasse); 
	boolean modifyCaracteristicClient (Long clientId, String clientType, boolean isLocalClient); 
	Client getClientById(Long id);
	boolean removeClient(Long id);
	Person getClientByConnexionPassword(String connexion, String password);
}
