package it.unicam.cs.ids.progetto;

import it.unicam.cs.ids.candidatura.Candidatura;
import it.unicam.cs.ids.candidatura.StatoCandidatura;
import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.utils.FakeDb;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GestoreProgetto {
    private static GestoreProgetto instance;
    private final FakeDb db = new FakeDb(); // fake db

    public GestoreProgetto() {
    }

    // Singleton
    public static GestoreProgetto getInstance() {
        if (instance == null) {
            instance = new GestoreProgetto();
        }
        return instance;
    }

    public Progetto createProgetto(String idProponente, String nome, String descrizione) {
        Progetto progettoNeutro = new Progetto(idProponente, nome, descrizione);
        db.addProgetto(progettoNeutro);
        return progettoNeutro;
    }

    public void requestEsperto(Progetto progettoNeutro) {
        progettoNeutro.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
    }

    public void insertInfoProgettisti(Progetto progetto, Map<Specializzazione, Integer> in) {
        progetto.setInfoProgettistiRichiesti(in);
    }

    /**
     * @param idProgetto
     * @return Progetto con id uguale a idProgetto, null se non esiste
     */
    public Progetto getProgetto(String idProgetto) {
        for (Progetto progetto : db.progetti) {
            if (progetto.getId().equals(idProgetto))
                return progetto;
        }
        return null;
    }

    public void pubblicaProgetto(Progetto progetto) {
        progetto.setStatoProgetto(StatoProgetto.PUBBLICO);
    }

    public Set<Progetto> getListaProgetti() {
        return db.progetti;
    }

    public Set<Progetto> getListaProgetti(String idProponente) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getIdProponente().equals(idProponente))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }

    public Set<Progetto> getListaProgetti(String idProponente, StatoProgetto stato) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getIdProponente().equals(idProponente) && progetto.getStatoProgetto().equals(stato))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }

    // Restituisce tutti i progetti aventi stato che richiedono una delle specializzazioni passate

    public Set<Progetto> getListaProgetti(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Specializzazione specializzazione : specializzazioni) {
            progettiCercati.addAll(getListaProgetti(specializzazione, statoProgetto));
        }
        return progettiCercati;
    }

    // Restituisce tutti i progetti aventi stato che richiedono la specializzazione passata

    private Set<Progetto> getListaProgetti(Specializzazione specializzazione, StatoProgetto statoProgetto) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getStatoProgetto().equals(statoProgetto) &&
                    progetto.getInfoProgettistiRichiesti().containsKey(specializzazione)) {
                progettiCercati.add(progetto);
            }
        }
        return progettiCercati;
    }

    public Set<Progetto> getListaProgetti(StatoProgetto statoProgetto) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getStatoProgetto().equals(statoProgetto))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }

    public Set<Progetto> getListaProgettiSvolti(Progettista progettista) {
        Set<Progetto> progettiSvolti = new HashSet<>();
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgettista().equals(progettista.getId()) && candidatura.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA)) {
                progettiSvolti.add(this.getProgetto(candidatura.getIdProgetto()));
            }
        }
        return progettiSvolti;
    }

    public Set<Progetto> getListaProgettiSvolti(String idProgettista) {
        Set<Progetto> progettiSvolti = new HashSet<>();
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgettista().equals(idProgettista) && candidatura.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA)) {
                progettiSvolti.add(this.getProgetto(candidatura.getIdProgetto()));
            }
        }
        return progettiSvolti;
    }

    public void notificaEsito(String idProgettista) {
        //TODO notificaEsito
    }

    public String getInfoProgetto(String idProgetto) {
        return this.getProgetto(idProgetto).getInfo();
    }

    public void modificaStatoProgetto(Progetto progetto, StatoProgetto statoProgetto){
        progetto.setStatoProgetto(statoProgetto);
    }

    public void modificaStatoProgetto(String idProgetto, StatoProgetto statoProgetto){
        Progetto progetto = this.getProgetto(idProgetto);
        progetto.setStatoProgetto(statoProgetto);
    }

}
