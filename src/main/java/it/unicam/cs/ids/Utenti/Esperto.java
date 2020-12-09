package it.unicam.cs.ids.Utenti;
import java.util.ArrayList;
import java.util.Scanner;


public class Esperto extends Utente{

	private String id;
	private String nome;
	private String cognome;
	private ArrayList<String> specializzazioni = new ArrayList<String>();
	
	public String getId()
	{
		return this.id;
	}
	public String getNome()
	{
		return this.nome;
	}
	public String getCognome()
	{
		return this.cognome;
	}
	public ArrayList<String> getSpecializzazioni()
	{
		return this.specializzazioni;
	}
	
	public Esperto(Utente utente, ArrayList<String> spec)
	{
		this.nome = utente.getNome();
		this.cognome = utente.getCognome();
		this.id = utente.getId();
		this.specializzazioni.addAll(spec);
	}
	public Esperto() {}
	
	public void valutaProgetti()
	{
		//richiama il metodo di gestoreProgetti che valuta i progetti?
	}
	
	public void valutaCandidatura()
	{
		//richiama il metodo di gestoreProgetti che valuta le candidature?
	}
	
	
	public void addSpecializzazione(String spec)
	{
		specializzazioni.add( spec);
	
	}
}

