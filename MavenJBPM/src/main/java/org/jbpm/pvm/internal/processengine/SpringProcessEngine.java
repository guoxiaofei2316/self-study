package org.jbpm.pvm.internal.processengine;

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

import org.hibernate.cfg.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.cfg.ConfigurationImpl;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.env.PvmEnvironment;
import org.jbpm.pvm.internal.env.SpringContext;
import org.jbpm.pvm.internal.wire.descriptor.ProvidedObjectDescriptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class SpringProcessEngine extends ProcessEngineImpl implements EnvironmentFactory, ProcessEngine {

	private static final Log log = Log.getLog(SpringProcessEngine.class.getName());

	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContext;

	public static ProcessEngine create(ConfigurationImpl configuration) {
		SpringProcessEngine springProcessEngine = null;

		ApplicationContext applicationContext = null;
		if (configuration.isInstantiatedFromSpring()) {
			applicationContext = (ApplicationContext) configuration.getApplicationContext();

			springProcessEngine = new SpringProcessEngine();
			springProcessEngine.applicationContext = applicationContext;
			springProcessEngine.initializeProcessEngine(configuration);

			LocalSessionFactoryBean localSessionFactoryBean = springProcessEngine.get(LocalSessionFactoryBean.class);
			Configuration hibernateConfiguration = localSessionFactoryBean.getConfiguration();
			springProcessEngine.processEngineWireContext.getWireDefinition()
					.addDescriptor(new ProvidedObjectDescriptor(hibernateConfiguration, true));

			springProcessEngine.checkDb(configuration);

		} else {
			String springCfg = (String) configuration.getProcessEngineWireContext().get("spring.cfg");
			if (springCfg == null) {
				springCfg = "applicationContext.xml";
			}
			applicationContext = new ClassPathXmlApplicationContext(springCfg);
			springProcessEngine = (SpringProcessEngine) applicationContext.getBean("processEngine");
		}

		return springProcessEngine;
	}

	public EnvironmentImpl openEnvironment() {
		PvmEnvironment environment = new PvmEnvironment(this);

		if (log.isTraceEnabled())
			log.trace("opening jbpm-spring" + environment);

		environment.setContext(new SpringContext(applicationContext));

		installAuthenticatedUserId(environment);
		installProcessEngineContext(environment);
		installTransactionContext(environment);

		return environment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> type) {
		String[] names = applicationContext.getBeanNamesForType(type);

		if (names.length >= 1) {

			if (names.length > 1 && log.isWarnEnabled()) {
				log.warn("Multiple beans for type " + type + " found. Returning the first result.");
			}

			return (T) applicationContext.getBean(names[0]);
		}

		return super.get(type);
	}

	@Override
	public Object get(String key) {
		if (applicationContext.containsBean(key)) {
			return applicationContext.getBean(key);
		}

		return super.get(key);
	}
}
