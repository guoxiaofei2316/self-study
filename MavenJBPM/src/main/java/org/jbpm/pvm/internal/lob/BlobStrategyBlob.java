package org.jbpm.pvm.internal.lob;

/**
 *此类了解决 JBPM4.4 和 hibernate 4 冲突的，
 * 其中
 * org.jbpm.pvm.internal.lob
 * org.jbpm.pvm.internal.processengine
 * org.jbpm.pvm.internal.wire.descriptor
 * 这三个包中的类也是同样原因
 * 因为jbpm4.4默认使用的是 hibernate 3
 * 
 * 
 */
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.JbpmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlobStrategyBlob implements BlobStrategy {

	public void set(byte[] bytes, Lob lob) {
		if (bytes != null) {
			lob.cachedBytes = bytes;
			// lob.blob = Hibernate.createBlob(bytes); --源码(hinernate3)--seven
//			lob.blob = sessionFactory.getCurrentSession().getLobHelper().createBlob(bytes);
			ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml"); 
            SessionFactory sessionFactory = (SessionFactory)factory.getBean("sessionFactory");  
            Session session = sessionFactory.openSession();  
            lob.blob = session.getLobHelper().createBlob(bytes);  
            session.close();
			
		}
	}

	public byte[] get(Lob lob) {
		if (lob.cachedBytes != null) {
			return lob.cachedBytes;
		}
		java.sql.Blob sqlBlob = lob.blob;
		if (sqlBlob != null) {
			try {
				return sqlBlob.getBytes(1, (int) sqlBlob.length());
			} catch (SQLException e) {
				throw new JbpmException("couldn't extract bytes out of blob", e);
			}
		}
		return null;
	}
}
