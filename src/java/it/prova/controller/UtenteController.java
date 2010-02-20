package it.prova.controller;

import java.io.IOException;
import java.util.List;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import it.prova.model.Utente;
import it.prova.model.UtenteQuery;

import org.codehaus.groovy.grails.commons.DefaultGrailsApplication;
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
	
	@RequestMapping("/checkUtente/{id}.dispatch")
	public ModelAndView indifferente(@PathVariable Long id) throws IOException, ResourceException, ScriptException {
		
		Session s = sessionFactory.getCurrentSession();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/hello");
		Utente u = (Utente)s.get(Utente.class, id);
		//Persona p = Persona.findAllByNome("cicciu");
		System.out.println(u);
		//List<Utente> lista = 
		mav.addObject(u);
		return mav;
	}
	/*
	@RequestMapping("/checkUtente.dispatch")
	public ModelAndView indifferente2(Utente u) {
		
		Utente u2 = UtenteQuery.findByLoginAndPwd(u.getLogin(), u.getPassword());
		System.out.println("..............indifferente2");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/autore/list");
		mav.addObject(u2);
		return mav;
	}
	*/
	@RequestMapping("/checkUtente.dispatch")
	public String indifferente3(Utente u) {
		
		Utente u2 = UtenteQuery.findByLoginAndPwd(u.getLogin(), u.getPassword());
		System.out.println("..............indifferente3");
		if(u2 != null){
			return "redirect:autore";
		}
		
		//return "redirect:autore";
		return "redirect:/";
	}

}
