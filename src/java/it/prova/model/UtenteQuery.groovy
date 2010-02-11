package it.prova.model;

class UtenteQuery {
	
	public static Utente findByLoginAndPwd(String login,String pwd){
		return Utente.findByLoginAndPassword(login,pwd)
	}

}
