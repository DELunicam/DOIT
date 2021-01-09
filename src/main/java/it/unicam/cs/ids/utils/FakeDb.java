package it.unicam.cs.ids.utils;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.candidatura.Candidatura;
import it.unicam.cs.ids.candidatura.StatoCandidatura;
import it.unicam.cs.ids.progetto.*;
import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.utenti.Proponente;
import it.unicam.cs.ids.valutazione.Valutazione;
import it.unicam.cs.ids.progetto.Specializzazione;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FakeDb {
    Set<Progetto> progetti = new HashSet<Progetto>();
    Set<Proponente> proponenti = new HashSet<Proponente>();
	Set<Progettista> progettisti = new HashSet<Progettista>();
	public Set<Candidatura> candidature = new HashSet<Candidatura>();
	public Set<Valutazione> valutazioni = new HashSet<Valutazione>();

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
		enzo.addSpecializzazione(Specializzazione.INGEGNERIA);
		Progettista daniele = new Progettista();
		daniele.setId("PROG3");
		daniele.setNome("daniele");
		daniele.addSpecializzazione(Specializzazione.CHIMICA);
		Progettista marco = new Progettista();
		marco.setId("PROG4");
		marco.setNome("marco");
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
		uno.setId("p1");
		uno.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
//		uno.setProgettista(daniele);
//		uno.setProgettista(enzo);
		uno.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
		uno.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);

		Progetto due = new Progetto(mario.getId(), "progettoB", "descrizione banale");
		due.setId("p2");
		due.setStatoProgetto(StatoProgetto.INATTIVO);
//		due.setProgettista(luca);
//		due.setProgettista(enzo);
		due.addSingleInfoProgettistiRichiesti(Specializzazione.MATEMATICA, 4);
		due.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 5);

		Progetto tre = new Progetto(mario.getId(), "progettoC", "descrizione banale");
		tre.setId("p3");
		tre.setStatoProgetto(StatoProgetto.PUBBLICO);
//		tre.setProgettista(daniele);
//		tre.setProgettista(enzo);

		tre.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
		tre.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);
		
		Progetto quattro = new Progetto(mario.getId(), "progettoD", "descrizione banale");
		quattro.setId("p4");
		quattro.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
//		quattro.setProgettista(daniele);
//		quattro.setProgettista(enzo);
		quattro.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
		quattro.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);

		Progetto cinque = new Progetto(mario.getId(), "progettoE", "descrizione banale");
		cinque.setId("p5");
		cinque.setStatoProgetto(StatoProgetto.NEUTRO);
//		cinque.setProgettista(daniele);
//		cinque.setProgettista(enzo);
		cinque.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
		cinque.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);

		this.progetti.add(uno);
		this.progetti.add(due);
		this.progetti.add(tre);
		this.progetti.add(quattro);
		this.progetti.add(cinque);

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
		sad.setStatoCandidatura(StatoCandidatura.RIFIUTATA);
		Candidatura zoo = new Candidatura();
		zoo.setIdProgettista(daniele.getId());
		zoo.setIdProgetto(due.getId());
		zoo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
		Candidatura ciao = new Candidatura();
		ciao.setIdProgettista(daniele.getId());
		ciao.setIdProgetto(tre.getId());
		ciao.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
		Candidatura puffo = new Candidatura();
		puffo.setIdProgettista(enzo.getId());
		puffo.setIdProgetto(tre.getId());
		puffo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
		Candidatura bee = new Candidatura();
		bee.setIdProgettista(daniele.getId());
		bee.setIdProgetto(uno.getId());
		bee.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);
		Candidatura poi = new Candidatura();
		poi.setIdProgettista(enzo.getId());
		poi.setIdProgetto(uno.getId());
		poi.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);


//		uno.setCandidatura(foo);
//		uno.setCandidatura(bar);
//		uno.setCandidatura(sad);
//		due.setCandidatura(zoo);
//		tre.setCandidatura(puffo);
//		tre.setCandidatura(ciao);
//		uno.setCandidatura(bee);
//		uno.setCandidatura(poi);
		
	}

	public void addProgetto(Progetto progetto){
    	this.progetti.add(progetto);
	}

	public void addCandidatura(Candidatura candidatura){
    	this.candidature.add(candidatura);
	}

	public void addValutazione(Valutazione valutazione){
    	this.valutazioni.add(valutazione);
	}

	// aggiunto per completare metodo getProgettisti() in GestoreCandidature -luca
	public Progettista selectProgettista(String idProgettista) {
		for (Progettista progettista : this.progettisti) {
            if (progettista.getId().equals(idProgettista))
            return progettista;
        }
        return null;
	}

}
