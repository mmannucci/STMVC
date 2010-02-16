@artifact.package@
import @artifact.classPkg@.@artifact.className@;
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
public class @artifact.name@ {

	@Autowired
	protected SessionFactory sessionFactory;

	@RequestMapping(value = "/@artifact.propertyName@.dispatch", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			modelMap.addAttribute("@artifact.propertyName@InstaceList", @artifact.className@.findAll(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) @artifact.className@.count() / sizeNo;
			modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			modelMap.addAttribute("@artifact.propertyName@InstanceList", @artifact.className@.list());
		}
		return "@artifact.propertyName@/list";
	}

	@RequestMapping(value = "/@artifact.propertyName@/create.dispatch", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.addAttribute("@artifact.propertyName@Instance", new @artifact.className@());
		return "@artifact.propertyName@/create";
	}

	@RequestMapping(value = "/@artifact.propertyName@.dispatch", method = RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap modelMap) {
		@artifact.className@ @artifact.propertyName@ = new @artifact.className@();
		MyUtils.bindDataFromMap(@artifact.propertyName@, request);
		if (!@artifact.propertyName@.validate()) {
			modelMap.addAttribute("@artifact.propertyName@Instance", @artifact.propertyName@);
			return "@artifact.propertyName@/create";
		}        
		@artifact.propertyName@.save();
		return "redirect:/@artifact.propertyName@/" + @artifact.propertyName@.getId() + ".dispatch";
	}

	@RequestMapping(value = "/@artifact.propertyName@/{id}.dispatch", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("@artifact.propertyName@", @artifact.className@.get(id));
		return "@artifact.propertyName@/show";
	}

	@RequestMapping(value = "/@artifact.propertyName@/edit{id}.dispatch", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("@artifact.propertyName@", @artifact.className@.get(id));
		return "@artifact.propertyName@/update";
	}

	@RequestMapping("/update@artifact.className@.dispatch")
	public String update(@RequestParam("id") Long id, HttpServletRequest request, ModelMap modelMap) {

		@artifact.className@ @artifact.propertyName@ = @artifact.className@.get(id);
		if (@artifact.propertyName@ == null) throw new IllegalArgumentException("A @artifact.propertyName@ is required");
		MyUtils.bindDataFromMap(@artifact.propertyName@, request);
		if (@artifact.propertyName@.validate()) {
			modelMap.addAttribute("@artifact.propertyName@", @artifact.propertyName@);
			return "@artifact.propertyName@/update";
		}
		@artifact.propertyName@.update();
		return "redirect:/@artifact.propertyName@" + @artifact.propertyName@.getId() + ".dispatch";
	}

	@RequestMapping(value = "/@artifact.propertyName@/{id}.dispatch", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		@artifact.className@.get(id).delete();
		return "redirect:/@artifact.propertyName@.dispatch?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
	}
}