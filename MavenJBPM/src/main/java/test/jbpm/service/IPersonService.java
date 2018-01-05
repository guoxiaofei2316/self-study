package test.jbpm.service;

import test.jbpm.api.MesUser;
import test.jbpm.api.Person;

public interface IPersonService {

	MesUser findPerson(String userId);
	 
	 public void start();
	 
	 public void findFlowAll();
	 
	 public void publishTask();
	 
	 public void addUserAndGroup();
	 
	 public void selectTask();
	 
	 public void completeTask();
}
