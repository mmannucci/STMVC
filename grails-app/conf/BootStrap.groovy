import it.prova.Persona;
import it.prova.model.Autore;
import it.prova.model.Libro;
import it.prova.model.Utente;

class BootStrap {
	
	def init = { servletContext ->
		
		
		new Persona(nome:"peppe",cognome:"rossi").save()
		new Persona(nome:"ciccio",cognome:"bianchi").save()
		new Persona(nome:"rocco",cognome:"barocco").save()
		
		new Utente(login:"m",password:"m",nome:"marcus",cognome:"dei").save()
		
		Autore a1=new Autore(nome:"marco",cognome:"mannux").save()
		Autore a2=new Autore(nome:"alberto",cognome:"multarix").save()
		
		new Libro(titolo:"ciao sumeri",autore:a2,pagine:5).save()
		Libro l2=new Libro(titolo:"secondo libro",pagine:6)
		a2.addToLibros(l2)
		
	}
	def destroy = {
	}
} 