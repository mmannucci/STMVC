package it.prova.controller;

import it.prova.model.Libro;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class  BaseController  {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//come secondo argomento di registerCustomEditor() si può mettere o null (=tutte le proprietà,qualunque nome
		//abbiano ma del tipo .class specificato) oppure una stringa col nome del parametro in 
		//request che va 'tradotto'
		binder.registerCustomEditor(Set.class, null, new CustomCollectionEditor(Set.class) {
	           protected Object convertElement(Object element) {
	        	   
	        	   try {
	        		   
					   if (element != null) {
					       Long id = new Long((String)element);
					       Object l =  sessionFactory.getCurrentSession().get(Libro.class, id);
					       return l;
					   }
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				return null;
	           }
	       });
		
	}



	

}
