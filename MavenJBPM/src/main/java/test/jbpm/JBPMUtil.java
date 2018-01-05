package test.jbpm;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;

public class JBPMUtil {

	private static ProcessEngine processEngine;
	private static RepositoryService repositoryService;
	private static ExecutionService executionService;
	private static IdentityService identityService;
	private static TaskService taskService;
	
	static{
		//流程定义引擎的初始化
		processEngine = Configuration.getProcessEngine();
		//管理流程定义
		repositoryService = processEngine.getRepositoryService();
		//executionService  用于执行流程定义实例
		executionService = processEngine.getExecutionService();
		//用户管理
		identityService = processEngine.getIdentityService();
		//节点
		taskService = processEngine.getTaskService();
	}
	
	
	/**
	 * 获取流程管理的repositoryService
	 * @return 
	 */
	public static RepositoryService getRepositoryService(){
		return repositoryService;
	}
	
	/**
	 * 获取执行流程定义的ExecutionService
	 * @return
	 */
	public static ExecutionService getExecutionService(){
		return executionService;
	}
	
	public static IdentityService getIdentityService(){
		return identityService;
	}
	
	public static TaskService getTaskService(){
		return taskService;
	}
}
