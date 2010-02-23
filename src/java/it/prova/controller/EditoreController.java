package it.prova.controller;


import it.prova.model.Editore;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("EditoreController")
@RequestMapping(value="/editore")
public class EditoreController {

	@Autowired
	protected SessionFactory sessionFactory;

	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public String list(@RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "max", required = false) Integer max,
			@RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order, HttpServletRequest request, ModelMap modelMap) {
		int sizeNo = max == null ? 10 : max.intValue();
		modelMap.addAttribute("editoreInstanceList", Editore.findAll(offset == null ? 0 : (offset.intValue() - 1), sizeNo, sort, order));
		modelMap.addAttribute("editoreInstanceTotal", Editore.count());
		
		return "/editore/list";
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.addAttribute("editoreInstance", new Editore());
		return "/editore/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap modelMap) {
		Editore editore = Editore.create();
		MyUtils.bindDataFromMap(editore, request);
		if (!editore.validate()) {
			modelMap.addAttribute("editoreInstance", editore);
			return "/editore/create";
		}        
		Long id = editore.save();

		return "redirect:/editore/" + id + ".dispatch";
	}

	@RequestMapping(value = {"/{id}.dispatch", "/show/{id}.dispatch"}, method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("editoreInstance", Editore.get(id));
		return "/editore/show";
	}


	
	@RequestMapping(value = "/edit", params="edit",method = RequestMethod.POST)
	public String edit(@RequestParam("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("editoreInstance", Editore.get(id));
		return "/editore/edit";
	}
	
	@RequestMapping(value = "/update", params="update",method = RequestMethod.POST)
	public String update(@RequestParam("id") Long id, HttpServletRequest request, ModelMap modelMap) {
		request.setAttribute(org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes.CONTROLLER_NAME_ATTRIBUTE, "editore");
		Editore editore = Editore.get(id);
		if (editore == null) throw new IllegalArgumentException("A editore is required");
		MyUtils.bindDataFromMap(editore, request);
		if (!editore.validate()) {
			modelMap.addAttribute("editoreInstance", editore);
			return "/editore/edit";
		}
		editore.update();
		return "redirect:/editore/" + editore.getId() + ".dispatch";
	}
	
	//Usato sia in show.gsp che in edit.gsp
	@RequestMapping(params="delete",method = RequestMethod.POST)
	public String delete(@RequestParam("id") Long id) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		Editore.get(id).delete();
		return "redirect:/editore/list.dispatch";
	}
	
	@RequestMapping(params="undo",method = RequestMethod.POST)
	public String undo() {
		return "redirect:/editore/list.dispatch";
	}
	
	
	
}