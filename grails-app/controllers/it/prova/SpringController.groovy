package it.prova;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class SpringController {
	
	@Autowired 
	SessionFactory sessionFactory
	
	/*
	@RequestMapping("/hello.dispatch") 
	ModelMap handleRequest() { 
		def session = sessionFactory.getCurrentSession() 
		return new ModelMap(session.get(Persona, 1L))
	}
	*/
	
}
