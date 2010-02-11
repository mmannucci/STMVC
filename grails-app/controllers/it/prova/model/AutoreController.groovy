package it.prova.model

class AutoreController {
	
	def scaffold = Autore
	
	def delete = {
		println "...................params di delete:"+params
		
	}
	def update = {
		Autore aut = new Autore(params)
		println "...................params di update:"+params
		println "...................autore:"+aut+aut.isAttached()
		
	}
}
