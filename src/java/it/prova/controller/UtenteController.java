package it.prova.controller;

import it.prova.model.Utente;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UtenteController {
	
//	@Autowired
//	private MyServices myServices;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@RequestMapping("/checkUtente.dispatch")
	public String login(Utente u) {
		
		try {
			//myServices.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Utente uy = new Utente();
		Utente ux = uy.findByLoginAndPwd(u.getLogin(), u.getPassword());
		
		if(ux != null){
			return "forward:/editore/list.dispatch";
		}
		
		return "redirect:/";
	}
 
}
