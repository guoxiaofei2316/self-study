package test.jbpm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.jbpm.api.Person;
import test.jbpm.dao.IUserDao;
import test.jbpm.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {

	@Autowired
    private IUserDao userDao;
	@Autowired
	private ProcessEngine processEngine;
	
	@Override
	public Person findPerson(int userId) {
        return userDao.get(userId, Person.class);
	}
	
	public void start(){
		RepositoryService repositoryService = processEngine.getRepositoryService();
		 NewDeployment createDeployment = repositoryService.createDeployment();
		NewDeployment addResourceFromClasspath = createDeployment.addResourceFromClasspath("jbpm/leave.jpdl.xml");
		String deploy = addResourceFromClasspath.deploy();
	}

}
