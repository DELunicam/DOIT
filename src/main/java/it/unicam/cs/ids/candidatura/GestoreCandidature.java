package it.unicam.cs.ids.candidatura;

import java.util.*;

import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.utils.FakeDb;

public class GestoreCandidature {
    private static GestoreCandidature instance;
    private FakeDb db = new FakeDb(); // fake db

    public GestoreCandidature() {

    }

    // Singleton
    public static GestoreCandidature getInstance() {
        if (instance == null) {
            instance = new GestoreCandidature();
        }
        return instance;
    }

    // --> sul diagramma sta in Candidatura, ma credo vada qui -luca
    // ritorna lista di tutte le candidature ad un progetto
    public Set<Candidatura> getCandidature(String idProgetto) {
        Set<Candidatura> candidature = new HashSet<Candidatura>();
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgetto().equals(idProgetto))
                candidature.add(candidatura);
        }
        return candidature;
    }
    
    // ritorna lista delle candidature ad un progetto che sono in un determinato stato
    public Set<Candidatura> getCandidature(String idProgetto, StatoCandidatura statoCandidatura) {
        Set<Candidatura> candidature = new HashSet<Candidatura>();
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgetto().equals(idProgetto) && candidatura.getStatoCandidatura().equals(statoCandidatura))
                candidature.add(candidatura);
        }
        return candidature;
    }

    // --> sul diagramma non c'è, aggiunto per praticità, sarebbe il select dal db -luca
    public Candidatura getCandidatura(String idCandidatura) {
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getId().equals(idCandidatura))
            return candidatura;
        }
        return null;
    }

    // --> sul diagramma non c'è, aggiunto per recuperare la candidatura in valutaProgettisti() -luca
    public Candidatura getCandidatura(String idProgetto, String idProgettista) {
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgetto().equals(idProgetto) && candidatura.getIdProgettista().equals(idProgettista))
            return candidatura;
        }
        return null;
    }

    // modifica lo stato di una candidatura
    public void modificaStatoCandidatura(String idCandidatura, StatoCandidatura statoCandidatura) {
        this.getCandidatura(idCandidatura).setStatoCandidatura(statoCandidatura);
    }

    // aggiunta, stava in gestore progettto come:
    // public void modificaStatoCandidatura(StatoCandidatura stato)
    public void modificaStatoCandidature(StatoCandidatura statoCandidatura, Set<Candidatura> candidature) {
        for (Candidatura candidatura : candidature) {
            candidatura.setStatoCandidatura(statoCandidatura);
        }
    }

    // ottieni una lista di progettisti data una lista di candidature
    public Set<Progettista> getProgettisti(Set<Candidatura> candidature) {
        Set<Progettista> progettisti = new HashSet<Progettista>();
        for (Candidatura candidatura : db.candidature) {
            progettisti.add(db.selectProgettista(candidatura.getIdProgettista()));
            /*
            Progettista progettista = db.selectProgettista(candidatura.getIdProgettista()); 
            if (!progettista.equals(null))
                progettisti.add(progettista);
                */
        }
        return progettisti;
    }

    // --> modificato tipo di ritorno da void a Candidatura -luca
    // crea una nuova candidatura ad un progetto
    public Candidatura creaCandidatura(String idProgettista, String idProgetto) {
        Candidatura nuovaCandidatura = new Candidatura(idProgetto, idProgettista);
        this.db.addCandidatura(nuovaCandidatura);
        return nuovaCandidatura;
    }

    // aggiunge a liste temporanee di candidature consigliate o meno
    public void addCandidatura(Candidatura candidatura, Set<Candidatura> set) {
        set.add(candidatura);
    }

    // aggiunge l'id dell'esperto che le ha approvato alle candidature e il parete positivo o negativo
    public void confermaSelezione(String idEsperto, Set<Candidatura> consigliate, Set<Candidatura> sconsigliate) {
        if (!consigliate.isEmpty()) {
            for (Candidatura candidatura : consigliate) {
                candidatura.addParereEsperto(idEsperto, true);
            }
        }
        if (!sconsigliate.isEmpty()) {
            for (Candidatura candidatura : sconsigliate) {
                candidatura.addParereEsperto(idEsperto, false);
            }
        }
    }

}
