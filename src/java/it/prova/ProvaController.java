package it.prova;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvaController {
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping("/helloWorld.dispatch")
	public ModelAndView helloWorld() {
		System.out.println("nel metodo.........");
		ModelAndView mav = new ModelAndView();
		//il nome della view è il nome del file gsp dentro views con lo \ davanti
		//mav.setViewName("/helloWorld");
		Persona p = new Persona();
		p.setCognome("cicciu");
		p.setNome("roccu");
		mav.addObject("persona2", p);
		return mav;
	}
	/*per far funzionare tutto cio ho dovuto modificare il Config.groovy per dire a
	 * spring di fare lo scanning in questo package alla ricerca di controllers 
	 */
	
	@RequestMapping(value="/hello.dispatch") 
	public ModelMap handleRequesti(Messaggio m,BindingResult b,@PathVariable String id) {
		System.out.println("nel mio metodo....."+id);
		Persona p = new Persona();
		p.setCognome("cicciu");
		p.setNome("roccukkk");
		//in questo modo nella view l'attributo passato ha nome=persona
		//perchè spring mvc segue la convenzione del nomedellaclasseminuscolo
		return new ModelMap(m);
		//quindi facendo persona.nome o persona.cognome avro la stampa desiderata
	}
	
	@RequestMapping("/helloHibernate.dispatch")
	public ModelAndView helloHibern() {
		System.out.println("nel metodo di hibernate.........");
		ModelAndView mav = new ModelAndView();
		//se commento questo la view la vede in automatico, se la forzo non va
		mav.setViewName("/helloWorld");
		
		Session session = sessionFactory.getCurrentSession();
				
		Persona p = (Persona) session.get(Persona.class, 2L);

		System.out.println(p);
		mav.addObject("persona2", p);
		return mav;
	}
}