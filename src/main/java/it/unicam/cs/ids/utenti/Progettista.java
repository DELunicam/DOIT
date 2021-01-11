package it.unicam.cs.ids.utenti;

import java.util.*;

import it.unicam.cs.ids.progetto.Specializzazione;

public class Progettista extends Utente
{

	private String id;
	private Set<Specializzazione> specializzazioni = new HashSet<Specializzazione>(); 
	
	public Progettista() {}
	public String getInfo()
	{	
		String info = "ID PROGETTISTA " +id+"\nSpecializzazioni:";
		for(Specializzazione spec : specializzazioni)
		{	
			info += spec.name() + " ";
		}
		return info;
	}

	//aggiunto
	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}
	
	public Set<Specializzazione> getSpecializzazioni()
	{
		return this.specializzazioni;
	}
	
	
	public Progettista(Utente utente, Set<Specializzazione> spec)
	
	{
		this.id = utente.getId();
		this.specializzazioni.addAll(spec);
	}
	
	public void addSpecializzazione(Specializzazione spec)
	{
		this.specializzazioni.add(spec);
	}
	
	
	
}
