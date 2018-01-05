package test.jbpm.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import test.jbpm.api.MesUser;
import test.jbpm.api.Person;
import test.jbpm.dao.IUserDao;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	
	/**
	 * @discription 更新数据
	 * @author admin-gxf       
	 * @created 2017年12月29日 下午3:41:53
	 */
	@Override
    public <T> void update(T t) {
        sessionFactory.getCurrentSession().update(t);
    }
    
    /**
     * @discription 根据ID查找数据
     * @param id
     * @param clazz
     * @return
     * @author admin-gxf       
     * @created 2017年12月29日 下午3:42:27
     */
	@Override
    @SuppressWarnings("unchecked")
    public MesUser  getUserByUserId(Serializable userId) {
		Session session = sessionFactory.getCurrentSession();
		List<MesUser> list = session.createQuery("from MesUser where USERID_= '"+userId+"'").list();
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
        return null;
    }
    
    /**
     * @discription 查找所有数据
     * @param clazz
     * @return
     * @author admin-gxf       
     * @created 2017年12月29日 下午3:43:27
     */
	@Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> clazz) {
        return sessionFactory.getCurrentSession().createQuery(clazz.toString()).list();
    }
    
    
    /**
     * @discription 保存数据
     * @param t
     * @author admin-gxf       
     * @created 2017年12月29日 下午3:44:24
     */
	@Override
    public <T> void save(T t) {
    	sessionFactory.getCurrentSession().save(t);
        
    }
}
