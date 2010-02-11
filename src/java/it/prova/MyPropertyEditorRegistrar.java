package it.prova;

import it.prova.model.Autore;
import it.prova.model.Libro;

import java.util.Set;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry; 

public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
    	System.out.println("......dentro registerCustomEditors");
        propertyEditorRegistry.registerCustomEditor(Set.class,"libros",new MyPropertyEditor(Set.class));
        //propertyEditorRegistry.registerCustomEditor(Book, new DomainClassLookupPropertyEditor(domainClass: Book, property: "isbn"))
        
    }
}