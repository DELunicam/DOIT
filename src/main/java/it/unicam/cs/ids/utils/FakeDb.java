package it.unicam.cs.ids.utils;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.progetto.Candidatura;
import it.unicam.cs.ids.progetto.StatoCandidatura;
import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.utenti.Proponente;
import it.unicam.cs.ids.progetto.Specializzazione;

public class FakeDb {
    Set<Progetto> progetti = new HashSet<Progetto>();
    Set<Proponente> proponenti = new HashSet<Proponente>();
    Set<Progettista> progettisti = new HashSet<Progettista>();

    public FakeDb() {
		
		// PROPONENTI
        Proponente mario = new Proponente();
		mario.setId("PROP1");
		Proponente dario = new Proponente();
		dario.setId("PROP2");
		
        this.proponenti.add(mario);
        this.proponenti.add(dario);

        // PROGETTISTI
		Progettista luca = new Progettista();
		luca.setId("PROG1");
		luca.setNome("luca");
		Progettista enzo = new Progettista();
		enzo.setId("PROG2");
		enzo.setNome("enzo");
		Progettista daniele = new Progettista();
		daniele.setId("PROG3");
		daniele.setNome("daniele");
		daniele.addSpecializzazione(Specializzazione.CHIMICA);
		Progettista marco = new Progettista();
		marco.setId("PROG4");
		marco.setNome("marco");
		marco.addSpecializzazione(Specializzazione.CHIMICA);
		Progettista paolo = new Progettista();
		paolo.setId("PROG5");
		paolo.setNome("paolo");
		
		this.progettisti.add(luca);
		this.progettisti.add(enzo);
		this.progettisti.add(daniele);
		this.progettisti.add(marco);
		this.progettisti.add(paolo);
		
		// UNA MAP
		HashMap<Specializzazione, Integer> map = new HashMap<Specializzazione, Integer>();
		map.put(Specializzazione.CHIMICA, 2);
		map.put(Specializzazione.INFORMATICA, 5);
		map.put(Specializzazione.INGEGNERIA, 1);
		map.put(Specializzazione.MATEMATICA, 3);
		
		// PROGETTI
		Progetto uno = new Progetto(mario.getId(), "progettoA", "descrizione banale");
		uno.setId("progettoPrimo");
		uno.setStatoProgetto(StatoProgetto.PUBBLICO);
		uno.setProgettista(daniele);
		uno.setProgettista(enzo);
		uno.setSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 3);
		uno.setSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);

		Progetto due = new Progetto(mario.getId(), "progettoB", "descrizione banale");
		due.setId("progettoSecondo");
		due.setStatoProgetto(StatoProgetto.INATTIVO);
		due.setProgettista(luca);
		due.setProgettista(enzo);
		due.setSingleInfoProgettistiRichiesti(Specializzazione.MATEMATICA, 4);
		due.setSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 5);

		this.progetti.add(uno);
		this.progetti.add(due);

		// CANDIDATURE
		Candidatura foo = new Candidatura();
		foo.setIdProgettista(luca.getId());
		foo.setIdProgetto(uno.getId());
		foo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
		Candidatura bar = new Candidatura();
		bar.setIdProgettista(marco.getId());
		bar.setIdProgetto(uno.getId());
		bar.setStatoCandidatura(StatoCandidatura.ACCETTATA);
		Candidatura sad = new Candidatura();
		sad.setIdProgettista(paolo.getId());
		sad.setIdProgetto(uno.getId());
		sad.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
		Candidatura zoo = new Candidatura();
		zoo.setIdProgettista(daniele.getId());
		zoo.setIdProgetto(due.getId());
		zoo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

		uno.setCandidatura(foo);
		uno.setCandidatura(bar);
		uno.setCandidatura(sad);
		due.setCandidatura(zoo);
	
	}

	public void add(Progetto progetto){
    	this.progetti.add(progetto);
	}

}
