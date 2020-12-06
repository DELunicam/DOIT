package ciao;
public class Proponente extends Utente
{
	private String idProponente;
	private String name;
	
	public String getId()
	{
		return idProponente;
	}
	
	public void setId(String id)
	{
		this.name = id;
	}
	
	public String getNome() 
	{
		return name;
	}
	
	public void setNome(String nome)
	
	{
		this.name = nome;
	}
	
	
	public Proponente (Utente utente)
	{
		this.setId(utente.getId());
		this.setNome(utente.getNome());
	}
	public Proponente() {}
}
