package it.unicam.cs.ids.candidatura;

import java.util.*;

import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.utils.FakeDb;

public class GestoreCandidature {
    
    private FakeDb db = new FakeDb(); // fake db

    public GestoreCandidature() {

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
    private Candidatura getCandidatura(String idCandidatura) {
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getId().equals(idCandidatura))
            return candidatura;
        }
        return null;
    }

    // modifica lo stato di una candidatura
    public void modificaStatoCandidatura(String idCandidatura, StatoCandidatura statoCandidatura) {
        this.getCandidatura(idCandidatura).setStatoCandidatura(statoCandidatura);
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

    // --> controllare il cambio di stato -luca
    // aggiunge l'id dell'esperto che le ha approvato alle candidature
    public void confermaSelezione(String idEsperto, Set<Candidatura> candidatureSelezionate) {
        for (Candidatura candidatura : candidatureSelezionate) {
            // candidatura.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA); <- cambiando lo stato la selezione può essere fatta da un solo esperto
            candidatura.addEsperto(idEsperto);
        }
    }

}
