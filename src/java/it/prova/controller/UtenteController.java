package it.prova.controller;

import java.io.IOException;
import java.util.List;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import it.prova.model.Utente;
import it.prova.model.UtenteQuery;

import org.codehaus.groovy.grails.commons.ControllerArtefactHandler;
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication;
import org.codehaus.groovy.grails.commons.GrailsControllerClass;
import org.codehaus.groovy.grails.commons.spring.GrailsApplicationContext;
import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UtenteController {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@RequestMapping("/checkUtente.dispatch")
	public String login(Utente u) {
		
		
		
		Utente ux = UtenteQuery.findByLoginAndPwd(u.getLogin(), u.getPassword());
		
		if(ux != null){
			return "forward:/editore/list.dispatch";
		}
		
		return "redirect:/";
	}
 
}
