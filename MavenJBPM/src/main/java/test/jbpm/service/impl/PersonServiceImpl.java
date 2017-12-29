package test.jbpm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.jbpm.api.Person;
import test.jbpm.dao.IUserDao;
import test.jbpm.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {

	@Autowired
    private IUserDao userDao;
	
	@Override
	public Person findPerson(int userId) {
        return userDao.get(userId, Person.class);
	}

}
