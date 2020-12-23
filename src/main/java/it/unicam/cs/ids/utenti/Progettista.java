package it.unicam.cs.ids.utenti;

import java.util.ArrayList;
import java.util.ListIterator;

import it.unicam.cs.ids.progetto.Specializzazione;

public class Progettista extends Utente
{

	private String id;
	private ArrayList<Specializzazione> specializzazioni = new ArrayList<Specializzazione>(); 
	private ArrayList<String> progettiSvolti = new ArrayList<String>(); ;
	public Progettista() {}
	/* public String getInfo()
	{
		String info = id;
		ListIterator<Specializzazione> i = specializzazioni.listIterator();
		int a = 0;
		while(i.hasNext() == true)
		{		
		info = info + " " + specializzazioni.get(a);
		a= a +1;
		i.next();
		}
		return info;
	}
		*/	
	//aggiunto
	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}
	
	
	public ArrayList<Specializzazione> getSpecializzazioni()
	{
		return this.specializzazioni;
	}
	public ArrayList<String> getProgetti()
	{
		return this.progettiSvolti;
	}
	
	public Progettista(Utente utente, ArrayList<Specializzazione> spec)
	
	{
		this.id = utente.getId();
		this.specializzazioni.addAll(spec);
	}
	
	public void addSpecializzazione(Specializzazione spec)
	{
		this.specializzazioni.add(spec);
	}
	
	public void addProgetti(String idProgetto)
	{
		this.progettiSvolti.add(idProgetto);
	}
	
	
	
}
