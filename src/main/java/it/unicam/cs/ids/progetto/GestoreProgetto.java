package it.unicam.cs.ids.progetto;

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

    // Restituisce tutti i progetti PUBBLICI che richiedono una delle specializzazioni passate
    // private Set<Progetto> getListaProgetti(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto)
    // forse e' meglio
    public Set<Progetto> getListaProgetti(Set<Specializzazione> specializzazioni) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Specializzazione specializzazione : specializzazioni) {
            progettiCercati.addAll(getListaProgetti(specializzazione));
        }
        return progettiCercati;
    }

    // Restituisce tutti i progetti PUBBLICI che richiedono la specializzazione passata
    // private Set<Progetto> getListaProgetti(Specializzazione specializzazione, StatoProgetto statoProgetto)
    // forse e' meglio
    private Set<Progetto> getListaProgetti(Specializzazione specializzazione) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getStatoProgetto().equals(StatoProgetto.PUBBLICO) &&
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

    public void notificaEsito(String idProgettista) {
        //TODO notificaEsito
    }

    public String getInfoProgetto(String idProgetto) {
        return this.getProgetto(idProgetto).getInfo();
    }



//    public Set<Candidatura> selezionaCandidatura(String idProgetto, StatoCandidatura statoCandidatura) {
//        Set<Candidatura> candidature = new HashSet<Candidatura>();
//        for (Progetto progetto : db.progetti) {
//            if (progetto.getId().equals(idProgetto)) {
//                for (Candidatura candidatura : progetto.getCandidature()) {
//                    if (candidatura.getStatoCandidatura().equals(statoCandidatura)) {
//                        candidature.add(candidatura);
//                    }
//                }
//            }
//        }
//        return candidature;
//    }
//
//    public void modificaStatoCandidatura(StatoCandidatura stato) {
//        for (Candidatura candidatura : candidature) {
//            candidatura.setStatoCandidatura(stato);
//        }
//    }



}
