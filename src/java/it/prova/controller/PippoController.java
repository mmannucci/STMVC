package it.prova.controller;


import it.prova.model.Pippo;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PippoController {

	@Autowired
	protected SessionFactory sessionFactory;

	@RequestMapping(value = "/pippo.dispatch", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			modelMap.addAttribute("pippoInstaceList", Pippo.findAll(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) Pippo.count() / sizeNo;
			modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			modelMap.addAttribute("pippoInstanceList", Pippo.list());
		}
		return "pippo/list";
	}

	@RequestMapping(value = "/pippo/create.dispatch", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.addAttribute("pippoInstance", new Pippo());
		return "pippo/create";
	}

	@RequestMapping(value = "/pippo.dispatch", method = RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap modelMap) {
		Pippo pippo = new Pippo();
		MyUtils.bindDataFromMap(pippo, request);
		if (!pippo.validate()) {
			modelMap.addAttribute("pippoInstance", pippo);
			return "pippo/create";
		}        
		pippo.save();
		return "redirect:/pippo/" + pippo.getId() + ".dispatch";
	}

	@RequestMapping(value = "/pippo/{id}.dispatch", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("pippo", Pippo.get(id));
		return "pippo/show";
	}

	@RequestMapping(value = "/pippo/edit{id}.dispatch", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("pippo", Pippo.get(id));
		return "pippo/update";
	}

	@RequestMapping("/updatePippo.dispatch")
	public String update(@RequestParam("id") Long id, HttpServletRequest request, ModelMap modelMap) {

		Pippo pippo = Pippo.get(id);
		if (pippo == null) throw new IllegalArgumentException("A pippo is required");
		MyUtils.bindDataFromMap(pippo, request);
		if (pippo.validate()) {
			modelMap.addAttribute("pippo", pippo);
			return "pippo/update";
		}
		pippo.update();
		return "redirect:/pippo" + pippo.getId() + ".dispatch";
	}

	@RequestMapping(value = "/pippo/{id}.dispatch", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		Pippo.get(id).delete();
		return "redirect:/pippo.dispatch?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
	}
}