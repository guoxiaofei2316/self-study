package test.jbpm.dao;

import java.io.Serializable;
import java.util.List;


public interface IUserDao {
	
	<T> T  get(Serializable id, Class<T> clazz);

	<T> void update(T t);
	
	<T> List<T> getAll(Class<T> clazz);
	
	 <T> void save(T t);
	
	 
	 <T> void delete(Serializable id, Class<T> clazz);
}
