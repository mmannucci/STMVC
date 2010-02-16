package it.prova.model

class PippoController {

    def scaffold = Pippo
	
	def delete = {
		println "...................params di delete:"+params
		
	}
	def update = {
		Pippo pippo = new Pippo(params)
		println "...................params di update:"+params
		println "...................autore:"+pippo+pippo.isAttached()
		
	}
}
