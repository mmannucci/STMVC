package it.prova.controller;


import it.prova.model.Autore;
import it.prova.model.Editore;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("AutoreController")
@RequestMapping(value="/autore")
public class AutoreController {


	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public String list(@RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "max", required = false) Integer max,
			@RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order, HttpServletRequest request, ModelMap modelMap) {
		int sizeNo = max == null ? 10 : max.intValue();
		modelMap.addAttribute("autoreInstanceList", Autore.findAll(offset == null ? 0 : (offset.intValue() - 1), sizeNo, sort, order));
		modelMap.addAttribute("autoreInstanceTotal", Autore.count());
		
		return "/autore/list";
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.addAttribute("autoreInstance", new Autore());
		return "/autore/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap modelMap) {
		Autore autore = Autore.create();
		MyUtils.bindDataFromMap(autore, request);
		if (!autore.validate()) {
			modelMap.addAttribute("autoreInstance", autore);
			return "/autore/create";
		}        
		Long id = autore.save();

		return "redirect:/autore/" + id + ".dispatch";
	}

	@RequestMapping(value = {"/{id}.dispatch", "/show/{id}.dispatch"}, method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("autoreInstance", Autore.get(id));
		return "/autore/show";
	}


	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("autoreInstance", Autore.get(id));
		return "/autore/edit";
	}
	
	@RequestMapping(value = "/update", params="update",method = RequestMethod.POST)
	public String update(@RequestParam("id") Long id, HttpServletRequest request, ModelMap modelMap) {
		Autore autore = Autore.get(id);
		if (autore == null) throw new IllegalArgumentException("A autore is required");
		MyUtils.bindDataFromMap(autore, request);
		if (!autore.validate()) {
			modelMap.addAttribute("autoreInstance", autore);
			return "/autore/edit";
		}
		autore.update();
		return "redirect:/autore/" + autore.getId() + ".dispatch";
	}
	
	//Usato sia in show.gsp che in edit.gsp
	@RequestMapping(params="delete",method = RequestMethod.POST)
	public String delete(@RequestParam("id") Long id) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		Autore.get(id).delete();
		return "redirect:/autore/list.dispatch";
	}
	
	@RequestMapping(params="undo",method = RequestMethod.POST)
	public String undo() {
		return "redirect:/autore/list.dispatch";
	}
	
	
	
}