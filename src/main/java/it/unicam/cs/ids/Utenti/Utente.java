package it.unicam.cs.ids.Utenti;

public class Utente 
{
	private String id;
	private String nome;
	private String cognome;
	private String mailAddress;
	public Utente(String id1, String name, String surname, String mailAdd )
	{
		this.setCognome(surname);
		this.setId(id1);
		this.setNome(name);
		this.setMailAddress(mailAdd);
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public Utente()
	{
		
	}


}
