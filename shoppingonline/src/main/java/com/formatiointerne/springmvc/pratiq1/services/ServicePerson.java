package com.formatiointerne.springmvc.pratiq1.services;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;

public interface ServicePerson {
	Person getPersonByConnexionPassword(String connexion, String password);
}
