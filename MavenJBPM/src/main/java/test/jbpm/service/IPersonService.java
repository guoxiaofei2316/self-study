package test.jbpm.service;

import test.jbpm.api.Person;

public interface IPersonService {

	 Person findPerson(int userId);
	 
	 public void start();
}
