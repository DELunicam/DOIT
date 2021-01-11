package it.unicam.cs.ids.utenti;
import it.unicam.cs.ids.progetto.Specializzazione;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Set;


public class Esperto extends Utente{

	private String id;
	private String nome;
	private String cognome;
	private Set<Specializzazione> specializzazioni = new HashSet<Specializzazione>();
	
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
	public Set<Specializzazione> getSpecializzazioni()
	{
		return this.specializzazioni;
	}
	
	public Esperto(Utente utente, Set<Specializzazione> spec)
	{
		this.nome = utente.getNome();
		this.cognome = utente.getCognome();
		this.id = utente.getId();
		this.specializzazioni.addAll(spec);
	}
	public Esperto() {}
	
	/* da implementare in IEsperto
	public void valutaProgetti()
	{
		
	}
	
	public void valutaCandidatura()
	{
		//richiama il metodo di gestoreProgetti che valuta le candidature?
	}
	
	*/
	public void addSpecializzazione(Specializzazione spec)
	{
		specializzazioni.add(spec);
	
	}
}

