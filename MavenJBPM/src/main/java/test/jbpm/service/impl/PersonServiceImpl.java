package test.jbpm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.jbpm.JBPMUtil;
import test.jbpm.api.MesUser;
import test.jbpm.api.Person;
import test.jbpm.dao.IUserDao;
import test.jbpm.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {

	@Autowired
    private IUserDao userDao;
	
	@Override
	public MesUser findPerson(String userId) {
        return userDao.getUserByUserId(userId);
	}
	
	public void start(){
		RepositoryService repositoryService = JBPMUtil.getRepositoryService();
		 NewDeployment createDeployment = repositoryService.createDeployment();
		NewDeployment addResourceFromClasspath = createDeployment.addResourceFromClasspath("jbpm/leave.jpdl.xml");
		String deploy = addResourceFromClasspath.deploy();
		System.err.println("部署的ID==》"+deploy);
	}
	
	public void findFlowAll(){
		List<ProcessDefinition> list = JBPMUtil.getRepositoryService().createProcessDefinitionQuery().orderAsc(ProcessDefinitionQuery.PROPERTY_KEY).list(); //排序条件  
		for(ProcessDefinition pro : list){
			System.out.println("id==>" + pro.getId()// 格式为：{key}-{version}  
                    + ", name==>" + pro.getName()// 流程定义文件（.jpdl.xml）中根元素的name属性的值  
                    + ", key==>" + pro.getKey()// 流程定义文件（.jpdl.xml）中根元素的key属性的值，可以不写，不写就是name属性的值  
                    + ", version==>" + pro.getVersion()// 流程定义文件（.jpdl.xml）中根元素的version属性的值，可以不写，不写就会自增（key相同时，版本加1）  
                    + ", deploymentId==>" + pro.getDeploymentId()); // 本流程定义对应的 .jpdl.xml 文件所属的Deployment对象的id  
		}
	}
	
	
	
	public void publishTask(){
		ExecutionService executionService = JBPMUtil.getExecutionService();
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("userId", "xiaobai");
		variables.put("mark", "哈哈哈");
		executionService.startProcessInstanceByKey("test", variables);
	}
	
	public void completeTask() {  
        String taskId = "15";  
        JBPMUtil.getTaskService().completeTask(taskId);  
    }
	
	/**
	 * @discription 查询未办理任务
	 * @author admin-gxf       
	 * @created 2018年1月3日 下午4:33:04
	 */
	public void selectTask(){
		TaskService taskService = JBPMUtil.getTaskService();
		List<Task> taskList = taskService.findPersonalTasks("xiaobai");
		// 显示任务  
        System.out.println("========= 【小白】的未办理的任务列表 =========");  
        for (Task task : taskList) {  
            System.err.println("id=" + task.getId()//  
                    + ", name=" + task.getName()//  
                    + ", assignee=" + task.getAssignee()//  
                    + ", createTime=" + task.getCreateTime());  
        } 
	}
	
	/**
	 * @discription 绑定用户与组
	 * @author admin-gxf       
	 * @created 2018年1月3日 下午4:21:27
	 */
	public void addUserAndGroup(){
		IdentityService identityService = JBPMUtil.getIdentityService();
		String createGroup = identityService.createGroup("user_dept");
		identityService.createUser("1","2", "3", "4");
		identityService.createUser("admin", "admin", "admin"); // 新建用户2
		identityService.createMembership("1", "user_dept"); // 绑定用户和部门的关系
		identityService.createMembership("admin", "user_dept"); // 绑定用户和部门的关系
	}
	
	

}
