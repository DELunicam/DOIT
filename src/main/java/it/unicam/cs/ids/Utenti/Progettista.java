package it.unicam.cs.ids.Utenti;

import java.util.ArrayList;
import java.util.ListIterator;

public class Progettista extends Utente
{

	private String id;
	private ArrayList<String> specializzazioni = new ArrayList<String>(); 
	private ArrayList<String> progettiSvolti = new ArrayList<String>(); ;
	public Progettista() {}
	public String getInfo()
	{
		String info = id;
		ListIterator<String> i = specializzazioni.listIterator();
		int a = 0;
		while(i.hasNext() == true)
		{		
		info = info + " " + specializzazioni.get(a);
		a= a +1;
		i.next();
		}
		return info;
	}
	
	public String getId()
	{
		return id;
	}
	
	
	public ArrayList<String> getSpecializzazioni()
	{
		return this.specializzazioni;
	}
	public ArrayList<String> getProgetti()
	{
		return this.progettiSvolti;
	}
	
	public Progettista(Utente utente, ArrayList<String> spec)
	
	{
		this.id = utente.getId();
		this.specializzazioni.addAll(spec);
	}
	
	public void addSpecializzazione(String spec)
	{
		this.specializzazioni.add(spec);
	}
	
	public void addProgetti(String idProgetto)
	{
		this.progettiSvolti.add(idProgetto);
	}
	
	
	
}
