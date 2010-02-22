
import it.prova.model.Autore;
import it.prova.model.Libro;
import it.prova.model.Utente;
import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.codehaus.groovy.grails.commons.spring.GrailsApplicationContext;

class BootStrap {
	
	def init = { servletContext ->
		

		
		new Utente(login:"m",password:"m",nome:"marcus",cognome:"dei").save()
		
		Autore a1=new Autore(nome:"marco",cognome:"mannux").save()
		Autore a2=new Autore(nome:"alberto",cognome:"multarix").save()
		
		new Libro(titolo:"ciao sumeri",autore:a2,pagine:5).save()
		Libro l2=new Libro(titolo:"secondo libro",pagine:6)
		a2.addToLibros(l2)
		
		10.times {new it.prova.model.Editore(nome:'a'+it).save()}
		
		
		
	}
	def destroy = {
	}
} 