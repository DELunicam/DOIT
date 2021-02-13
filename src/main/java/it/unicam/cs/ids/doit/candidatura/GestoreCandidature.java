package it.unicam.cs.ids.doit.candidatura;

import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;

@Service
public class GestoreCandidature {
    private static GestoreCandidature instance;

    @Autowired
    CandidaturaRepository repository;

    public GestoreCandidature() {

    }

    // Singleton
    public static GestoreCandidature getInstance() {
        if (instance == null) {
            instance = new GestoreCandidature();
        }
        return instance;
    }
    public Set<Long> getIdProgetti(Long idProgettista, StatoCandidatura stato) 
    {   Set<Long> id = new HashSet<Long>();
        Set<Candidatura> candidature = repository.findIdProgettiByIdProgettistaAndStatoCandidatura(idProgettista, stato);
        for (Candidatura p : candidature)
        {
            id.add(p.getIdProgetto());
        }
        return id;
    }

    // ritorna lista di tutte le candidature ad un progetto
    public Set<Candidatura> getCandidature(Long idProgetto) {
        return repository.findCandidatureByIdProgetto(idProgetto);
    }

    // ritorna lista delle candidature ad un progetto che sono in un determinato stato
    public Set<Candidatura> getCandidature(Long idProgetto, StatoCandidatura statoCandidatura) {
        return repository.findCandidatureByIdProgettoAndStato(idProgetto, statoCandidatura);
    }

    // ottiene candidatura da id
    public Candidatura getCandidatura(Long idCandidatura) {
        return repository.findCandidaturaById(idCandidatura);
    }

    // ottiene candidatura da id del progetto e id del progettista
    public Candidatura getCandidatura(Long idProgetto, Long idProgettista) {
        return repository.findCandidaturaByIdProgettoAndIdProgettista(idProgetto, idProgettista);
    }

    // modifica lo stato di una candidatura
    public void modificaStatoCandidatura(Long idCandidatura, StatoCandidatura statoCandidatura) {
        Candidatura candInDB = repository.findCandidaturaById(idCandidatura);//.get();
        candInDB.setStatoCandidatura(statoCandidatura);
        repository.save(candInDB);
    }

    // modifica lo stato di un gruppo di candidature
    public void modificaStatoCandidature(StatoCandidatura statoCandidatura, Set<Long> idCandidature) {
        //TODO
        for (Long idCandidatura : idCandidature) { // set di candidature passate
            Candidatura candidatura = repository.findCandidaturaById(idCandidatura);
            candidatura.setStatoCandidatura(statoCandidatura); 
            repository.save(candidatura); // modifica candidatura in db
        }
    }

    // TODO RIMUOVERE? DOVE SERVE?
    // ottieni una lista di progettisti data una lista di candidature (o meglio idCandidature)
    public Set<Progettista> getProgettisti(Set<Long> idsCandidature) {
        //return repository.findProgettistiByIdsCandidatureInCandidature(candidature);
        return null;
    }

    // crea una nuova candidatura ad un progetto
    public Candidatura creaCandidatura(Long idProgettista, Long idProgetto) {
        Candidatura nuovaCandidatura = new Candidatura(idProgetto, idProgettista);
        repository.save(nuovaCandidatura);
        return nuovaCandidatura;
    }

    // aggiunge a liste temporanee di candidature consigliate o meno
    public void addCandidatura(Long idCandidatura, Set<Long> idsCandidature) {
        idsCandidature.add(idCandidatura); // non va sul db -> no repository
    }

    // aggiunge alle candidature l'id dell'esperto che le ha valutate e il parete positivo o negativo
    public void confermaSelezione(Long idEsperto, Set<Long> consigliate, Set<Long> sconsigliate) {
        if (!consigliate.isEmpty()) {
            for (Long id : consigliate) {
                Candidatura candidatura = repository.findCandidaturaById(id);
                candidatura.addParereEsperto(idEsperto, true);
                repository.save(candidatura); // modifica candidatura in db
            }
        }
        if (!sconsigliate.isEmpty()) {
            for (Long id : sconsigliate) {
                Candidatura candidatura = repository.findCandidaturaById(id);
                candidatura.addParereEsperto(idEsperto, false);
                repository.save(candidatura); // modifica candidatura in db
            }
        }
    }


}
