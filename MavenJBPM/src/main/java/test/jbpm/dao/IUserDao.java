package test.jbpm.dao;

import java.io.Serializable;
import java.util.List;

import test.jbpm.api.MesUser;


public interface IUserDao {
	
	MesUser getUserByUserId(Serializable id);

	<T> void update(T t);
	
	<T> List<T> getAll(Class<T> clazz);
	
	 <T> void save(T t);
	
}
