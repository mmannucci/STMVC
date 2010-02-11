package it.prova.controller;

import it.prova.model.Autore;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutoreController {

	@Autowired
	protected SessionFactory sessionFactory;

	/*
	 * @RequestMapping("/updateUtente.dispatch") public String
	 * update(@RequestParam("id") Long id,Autore aut,BindingResult
	 * b,HttpServletRequest request) {
	 * System.out.println("................ingresso");
	 * System.out.println(".................size:"+aut.getLibros().size());
	 * 
	 * //TODO: capire il comportamento del bindingresult if(b.hasErrors()){
	 * System.out.println("............errori:"+b.getErrorCount()); }
	 * 
	 * 
	 * Session s = sessionFactory.getCurrentSession();
	 * System.out.println("...............aggiorna"); //Autore autorePers =
	 * (Autore) s.get(Autore.class, id); s.merge(aut);
	 * 
	 * return "redirect:autore"; }
	 */

	@RequestMapping("/updateUtente.dispatch")
	public ModelAndView update(@RequestParam("id") Long id,
			HttpServletRequest request) {

		// Autore a = new Autore(id);
		// Session s = sessionFactory.getCurrentSession();
		// Autore a = (Autore)s.get(Autore.class, id);
		// BindingResult res = DataBindingUtils.bindObjectToInstance(a,
		// request);
		Autore a = Autore.get(1L);
		MyUtils.bindDataFromMap(a, request);
		a.validate();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/autore/edit");
		mv.addObject("autoreInstance", a);
		return mv;
		// s.merge(a);
		// return "redirect:autore";
	}

	/*
	 * @RequestMapping("/updateUtente.dispatch") public String update(Autore
	 * a,HttpServletRequest request) { //Autore a = new Autore(id); Session s =
	 * sessionFactory.getCurrentSession(); //Autore a =
	 * (Autore)s.get(Autore.class, id); //BindingResult res =
	 * DataBindingUtils.bindObjectToInstance(a, request);
	 * //System.out.println("....second version"); //System.out.println("....."
	 * + res);
	 * 
	 * //System.out.println("...autore2:" + a);
	 * 
	 * System.out.println("...............aggiorna");
	 * it.test.MyUtils.bindDataFromMap(a, request); /*ValidatorFactory factory =
	 * Validation.buildDefaultValidatorFactory(); Validator validator =
	 * factory.getValidator();
	 * 
	 * Set<ConstraintViolation<Autore>> constraintViolations
	 * =validator.validate(a);
	 * System.out.println("...............set violation:" +
	 * constraintViolations);
	 * System.out.println("...............set violationnnnnnn:" + a.validate());
	 * s.merge(a); return "redirect:autore"; }
	 */
	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * System.out.println("nell'init binder "); super.initBinder(binder); }
	 */

}
