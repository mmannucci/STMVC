package it.prova;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import it.prova.model.Libro;

public class MyPropertyEditor extends CustomCollectionEditor {
	public MyPropertyEditor(Class collectionType) {
		
		super(collectionType);
		System.out.println(".......dentro costr di MyPropertyEditor");
		// TODO Auto-generated constructor stub
	}
	/*
	@Override
	protected boolean alwaysCreateNewCollection() {
		return true;
	}*/

	protected Object convertElement(Object element) {
		System.out.println("*************dentro convertElement" + element);
 	   try {
 		   
			   if (element != null) {
			       Long id = new Long((String)element);
			      // Object l =  sessionFactory.getCurrentSession().get(Libro.class, id);
			       
			       return new String(".....");
			   }
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
    }

}
