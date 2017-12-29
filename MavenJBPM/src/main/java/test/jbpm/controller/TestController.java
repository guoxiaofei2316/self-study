package test.jbpm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value="/find")
	public void find(HttpServletRequest request, HttpServletResponse response){
		
		Person findPerson = personService.findPerson(1);
		
		String s ="";
		
	}
	
}
