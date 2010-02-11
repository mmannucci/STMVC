package it.prova;

class Persona {
	
	String nome
	String cognome
	int eta
	
	public static Persona findByNome(String nome){
		return Persona.findByNome(nome)
	}
	
	public static Object methodInvoker(String methodName,String classType, Object... o){
		
		Class.forName(classType).'$methodName' o.join(',')		
	}
	@Override
	public String toString() {
		return "Persona [cognome=" + cognome + ", eta=" + eta + ", nome="+ nome + "]";
	}
	
	

}
