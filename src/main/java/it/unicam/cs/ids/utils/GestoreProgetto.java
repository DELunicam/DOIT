package it.unicam.cs.ids.utils;

import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.Candidatura;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utenti.Progettista;

import java.util.*;

public class GestoreProgetto {
    private Set<Progetto> progetti;
    private FakeDb db = new FakeDb(); // fake db
    public GestoreProgetto(){
        this.progetti = new HashSet<>();
    }
    public GestoreProgetto(Set<Progetto> progetti) {
        this.progetti = progetti;
    }

    public Progetto createProgetto(String idProponente, String nome, String descrizione) {
        //TODO createProgetto
        Progetto progettoNeutro = new Progetto(idProponente, nome, descrizione);
        this.progetti.add(progettoNeutro);
        return progettoNeutro;
    }

    public void requestEsperto(Progetto progettoNeutro) {
        //TODO requestEsperto
    }

    public void insertInfoProgettisti(Progetto progetto, Map<Specializzazione, Integer> in) {
        //TODO insertInfoProgettisti
        progetto.setInfoProgettistiRichiesti(in);
    }

    /**
     *
     * @param idProgetto
     * @return Progetto con id uguale a idProgetto, null se non esiste
     */
    public Progetto getProgetto(String idProgetto) {
        //TODO getProgetto
        for (Progetto progetto : db.progetti) {
            if (progetto.getId().equals(idProgetto))
                return progetto;
        }
        return null;
    }

    public void pubblicaProgetto(Progetto progetto) {
        //TODO pubblicaProgetto
    }

    public Set<Progetto> getListaProgetti(String idProponente){
        Set<Progetto> progettiCercati = new HashSet<Progetto>();
        /*
        for (Progetto progetto : this.progetti) {
            if (progetto.getIdProponente().equals(idProponente))
                progettiCercati.add(progetto);
        }
        */
        // per test
        for (Progetto progetto : db.progetti) {
            if (progetto.getIdProponente().equals(idProponente))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }

    public Set<Progetto> getListaProgetti(String idProponente, StatoProgetto stato) {
        Set<Progetto> progettiCercati = new HashSet<Progetto>();
        /*
        for (Progetto progetto : this.progetti) {
            if (progetto.getIdProponente().equals(idProponente) && progetto.getStatoProgetto().equals(stato))
                progettiCercati.add(progetto);
        }
        */
        // per test
        for (Progetto progetto : db.progetti) {
            if (progetto.getIdProponente().equals(idProponente) && progetto.getStatoProgetto().equals(stato))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }

    public Set<Candidatura> selezionaCandidatura(String idProgetto, StatoProgetto stato){
        Set<Candidatura> candidature = new HashSet<Candidatura>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getId().equals(idProgetto) && (progetto.getStatoProgetto().equals(stato)))
                candidature =  progetto.getCandidature();
        }
        return candidature;
    }

    public void modificaStatoCandidatura(StatoProgetto stato){
        //TODO modificaStatoCandidatura
    }

    // serve per prendere le info data una candidatura, aggiungere al diagramma
    public Progettista getProgettista(String idProgettista) {
        for (Progettista progettista : db.progettisti) {
            if (progettista.getId().equals(idProgettista))
                return progettista;
        }
        return null;
    }

    public String getInfoProgettisti(Set<Progettista> progettisti) {
        String info = "INFO PROGETTISTI:\n";
        for (Progettista progettista : progettisti) {
            info += progettista.getInfo() + "\n";
        }
        return info;
    }

    public void notificaEsito(String idProgettista) {
        //TODO notificaEsito
    }
}
