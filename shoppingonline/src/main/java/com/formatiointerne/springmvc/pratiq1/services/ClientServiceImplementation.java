package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Service;

import com.formatiointerne.springmvc.pratiq1.datamodels.Client;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;

@Service
public class ClientServiceImplementation implements ClientService {
	public List<Client> clients = new LinkedList<>();
	
	@Autowired
	ServicePersonImplementation servicePerson;
	
	public ClientServiceImplementation() {
		
	}

	@Override
	public Client createNewClient(String name, String sex, LocalDate birthDate, String adress, String telephone,
			String connexionname, String motdepasse, String clientType, boolean isLocalClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyIdentityClient(Long clientId, String name, String sex, LocalDate birthDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyAdressClient(Long clientId, String adress, String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyConnexionIdentifiants(Long clientId, String connexionname, String motdepasse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyCaracteristicClient(Long clientId, String clientType, boolean isLocalClient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client getClientById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeClient(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}