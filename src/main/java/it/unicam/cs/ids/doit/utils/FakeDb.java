//package it.unicam.cs.ids.doit.utils;
//
//import it.unicam.cs.ids.doit.candidatura.Candidatura;
//import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
//import it.unicam.cs.ids.doit.utenti.Progettista;
//import it.unicam.cs.ids.doit.progetto.Progetto;
//import it.unicam.cs.ids.doit.progetto.Specializzazione;
//import it.unicam.cs.ids.doit.progetto.StatoProgetto;
//import it.unicam.cs.ids.doit.utenti.Proponente;
//import it.unicam.cs.ids.doit.valutazione.Valutazione;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Set;
//
//public class FakeDb {
//	public Set<Progetto> progetti = new HashSet<Progetto>();
//	public Set<Proponente> proponenti = new HashSet<Proponente>();
//	public Set<Progettista> progettisti = new HashSet<Progettista>();
//	public Set<Candidatura> candidature = new HashSet<Candidatura>();
//	public Set<Valutazione> valutazioni = new HashSet<Valutazione>();
//
//	public FakeDb() {
//
//		// PROPONENTI
//		Proponente mario = new Proponente();
//		//mario.setId("PROP1");
//
//		Proponente dario = new Proponente();
//		//dario.setId("PROP2");
//
//        this.proponenti.add(mario);
//        this.proponenti.add(dario);
//
//
//        // PROGETTISTI
//		Progettista luca = new Progettista();
//		luca.setUsername("PROG1");
//		luca.setNome("luca");
//
//		Progettista enzo = new Progettista();
//		enzo.setUsername("PROG2");
//		enzo.setNome("enzo");
//		enzo.addSpecializzazione(Specializzazione.INGEGNERIA);
//
//		Progettista daniele = new Progettista();
//		daniele.setUsername("PROG3");
//		daniele.setNome("daniele");
//		daniele.addSpecializzazione(Specializzazione.CHIMICA);
//
//		Progettista marco = new Progettista();
//		marco.setUsername("PROG4");
//		marco.setNome("marco");
//
//		Progettista paolo = new Progettista();
//		paolo.setUsername("PROG5");
//		paolo.setNome("paolo");
//
//		this.progettisti.add(luca);
//		this.progettisti.add(enzo);
//		this.progettisti.add(daniele);
//		this.progettisti.add(marco);
//		this.progettisti.add(paolo);
//
//
//		// UNA MAP
//		HashMap<Specializzazione, Integer> map = new HashMap<Specializzazione, Integer>();
//		map.put(Specializzazione.CHIMICA, 2);
//		map.put(Specializzazione.INFORMATICA, 5);
//		map.put(Specializzazione.INGEGNERIA, 1);
//		map.put(Specializzazione.MATEMATICA, 3);
//
//
//		// PROGETTI
//		Progetto uno = new Progetto(mario.getUsername(), "progettoA", "descrizione banale");
////		uno.setId("p1");
//		uno.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
//		uno.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
//		uno.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);
//
//		Progetto due = new Progetto(mario.getUsername(), "progettoB", "descrizione banale");
////		due.setId("p2");
//		due.setStatoProgetto(StatoProgetto.INATTIVO);
//		due.addSingleInfoProgettistiRichiesti(Specializzazione.MATEMATICA, 4);
//		due.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 5);
//
//		Progetto tre = new Progetto(mario.getUsername(), "progettoC", "descrizione banale");
////		tre.setId("p3");
//		tre.setStatoProgetto(StatoProgetto.PUBBLICO);
//		tre.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
//		tre.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);
//
//		Progetto quattro = new Progetto(mario.getUsername(), "progettoD", "descrizione banale");
////		quattro.setId("p4");
//		quattro.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
//		quattro.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
//		quattro.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);
//
//		Progetto cinque = new Progetto(mario.getUsername(), "progettoE", "descrizione banale");
////		cinque.setId("p5");
//		cinque.setStatoProgetto(StatoProgetto.NEUTRO);
//		cinque.addSingleInfoProgettistiRichiesti(Specializzazione.INGEGNERIA, 1);
//		cinque.addSingleInfoProgettistiRichiesti(Specializzazione.CHIMICA, 1);
//
//		this.progetti.add(uno);
//		this.progetti.add(due);
//		this.progetti.add(tre);
//		this.progetti.add(quattro);
//		this.progetti.add(cinque);
//
//		// CANDIDATURE
//		Candidatura foo = new Candidatura();
//		//foo.setId("CAND1");
//		foo.setIdProgettista(luca.getUsername());
////		foo.setIdProgetto(uno.getId());
//		foo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
//
//		Candidatura bar = new Candidatura();
//		//bar.setId("CAND2");
//		bar.setIdProgettista(marco.getUsername());
////		bar.setIdProgetto(uno.getId());
//		bar.setStatoCandidatura(StatoCandidatura.ACCETTATA);
//
//		Candidatura sad = new Candidatura();
//		//sad.setId("CAND3");
//		sad.setIdProgettista(paolo.getUsername());
////		sad.setIdProgetto(uno.getId());
//		sad.setStatoCandidatura(StatoCandidatura.RIFIUTATA);
//
//		Candidatura zoo = new Candidatura();
//		//zoo.setId("CAND4");
//		zoo.setIdProgettista(daniele.getUsername());
////		zoo.setIdProgetto(due.getId());
//		zoo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
//
//		Candidatura ciao = new Candidatura();
//		//ciao.setId("CAND5");
//		ciao.setIdProgettista(daniele.getUsername());
////		ciao.setIdProgetto(tre.getId());
//		ciao.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
//
//		Candidatura puffo = new Candidatura();
//		//puffo.setId("CAND6");
//		puffo.setIdProgettista(enzo.getUsername());
////		puffo.setIdProgetto(tre.getId());
//		puffo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
//
//		Candidatura bee = new Candidatura();
//		//bee.setId("CAND7");
//		bee.setIdProgettista(daniele.getUsername());
////		bee.setIdProgetto(uno.getId());
//		bee.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);
//
//		Candidatura poi = new Candidatura();
//		//poi.setId("CAND8");
//		poi.setIdProgettista(enzo.getUsername());
////		poi.setIdProgetto(uno.getId());
//		poi.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
//
//		this.candidature.add(foo);
//		this.candidature.add(bar);
//		this.candidature.add(sad);
//		this.candidature.add(zoo);
//		this.candidature.add(ciao);
//		this.candidature.add(puffo);
//		this.candidature.add(bee);
//		this.candidature.add(poi);
//
//	}
//
//	public void addProgetto(Progetto progetto){
//    	this.progetti.add(progetto);
//	}
//
//	public void addCandidatura(Candidatura candidatura){
//    	this.candidature.add(candidatura);
//	}
//
//	public void addValutazione(Valutazione valutazione){
//    	this.valutazioni.add(valutazione);
//	}
//
//	// aggiunto per completare metodo getProgettisti() in GestoreCandidature -luca
//	public Progettista selectProgettista(String idProgettista) {
//		for (Progettista progettista : this.progettisti) {
//            if (progettista.getUsername().equals(idProgettista))
//            return progettista;
//        }
//        return null;
//	}
//
//}
