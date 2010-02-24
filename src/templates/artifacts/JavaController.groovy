import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@artifact.package@
import @artifact.classPkg@.@artifact.className@;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.groovy.grails.commons.ApplicationHolder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("@artifact.name@")
@RequestMapping(value="/@artifact.propertyName@")
public class @artifact.name@ {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "max", required = false) Integer max,
			@RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order, ModelMap modelMap) {
		int sizeNo = max == null ? 10 : max.intValue();
		modelMap.addAttribute("@artifact.propertyName@InstanceList", @artifact.className@.findAll(offset == null ? 0 : offset.intValue(), sizeNo));
		modelMap.addAttribute("@artifact.propertyName@InstanceTotal", @artifact.className@.count());
		return "/@artifact.propertyName@/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.addAttribute("@artifact.propertyName@Instance", new @artifact.className@());
		return "/@artifact.propertyName@/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap modelMap) {
		@artifact.className@ @artifact.propertyName@ = @artifact.className@.create();
		MyUtils.bindDataFromMap(@artifact.propertyName@, request);
		if (!@artifact.propertyName@.validate()) {
			modelMap.addAttribute("@artifact.propertyName@Instance", @artifact.propertyName@);
			return "/@artifact.propertyName@/create";
		}        
		Long id = @artifact.propertyName@.save();
		return "redirect:/@artifact.propertyName@/" + id + ".dispatch";
	}

	@RequestMapping(value = {"/{id}", "/@artifact.propertyName@/show/{id}"}, method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("@artifact.propertyName@Instance", @artifact.className@.get(id));
		return "/@artifact.propertyName@/show";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("@artifact.propertyName@Instance", @artifact.className@.get(id));
		return "/@artifact.propertyName@/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("id") Long id, HttpServletRequest request, ModelMap modelMap) {

		@artifact.className@ @artifact.propertyName@ = @artifact.className@.get(id);
		if (@artifact.propertyName@ == null) throw new IllegalArgumentException("A @artifact.propertyName@ is required");
		MyUtils.bindDataFromMap(@artifact.propertyName@, request);
		if (!@artifact.propertyName@.validate()) {
			modelMap.addAttribute("@artifact.propertyName@Instance", @artifact.propertyName@);
			return "/@artifact.propertyName@/edit";
		}
		@artifact.propertyName@.update();
		return "redirect:/@artifact.propertyName@/" + @artifact.propertyName@.getId() + ".dispatch";
	}

	@RequestMapping(value = "/@artifact.propertyName@/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Long id, @RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "max", required = false) Integer max) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		@artifact.className@.get(id).delete();
		return "redirect:/@artifact.propertyName@.dispatch?offset=" + ((offset == null) ? "1" : offset.toString()) + "&max=" + ((max == null) ? "10" : max.toString());
	}
}