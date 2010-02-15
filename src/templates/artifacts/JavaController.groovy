@artifact.package@ 
import @artifact.classPkg@.@artifact.className@;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
	
@Controller
public class @artifact.name@ {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@RequestMapping("/update@artifact.className@.dispatch")
	public ModelAndView update(@RequestParam("id") Long id, HttpServletRequest request) {
		
		@artifact.className@ @artifact.propertyName@ = @artifact.className@.get(1L);
		MyUtils.bindDataFromMap(@artifact.propertyName@, request);
		@artifact.propertyName@.validate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/@artifact.propertyName@/edit");
		mv.addObject("@artifact.propertyName@Instance", @artifact.propertyName@);
		return mv;
		// s.merge(@artifact.propertyName@);
		// return "redirect:@artifact.propertyName@";
	}
}
