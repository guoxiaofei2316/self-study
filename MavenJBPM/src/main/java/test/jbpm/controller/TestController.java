package test.jbpm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Before;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.jbpm.api.Person;
import test.jbpm.service.IPersonService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private IPersonService personService;
	
	
	@RequestMapping(value="/start")
	public void start(HttpServletRequest request, HttpServletResponse response){
		
		personService.start();		
		
	}
	
	@RequestMapping(value="/findFlowAll")
	public void findFlowAll(HttpServletRequest request, HttpServletResponse response){
		
		personService.findFlowAll();		
		
	}
	
	@RequestMapping(value="/publishTask")
	public void publishTask(HttpServletRequest request, HttpServletResponse response){
		
		personService.publishTask();		
		
	}
	
	
	@RequestMapping(value="/find")
	public void find(HttpServletRequest request, HttpServletResponse response){
		
//		Person findPerson = personService.findPerson(1);
		
		String s ="";
		
	}
	
	@RequestMapping(value="/selectTask")
	public void selectTask(HttpServletRequest request, HttpServletResponse response){
		
		personService.selectTask();
		
		String s ="";
		
	}
	
	@RequestMapping(value="/completeTask")
	public void completeTask(HttpServletRequest request, HttpServletResponse response){
		
		personService.completeTask();
		
		String s ="";
		
	}
	
	
	@RequestMapping(value="/addUserAndGroup")
	public void addUserAndGroup(HttpServletRequest request, HttpServletResponse response){
		
		personService.addUserAndGroup();
		
		String s ="";
		
	}
}
