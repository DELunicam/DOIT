package it.unicam.cs.ids.doit.candidatura;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepository extends JpaRepository<Candidatura, Long> {

    Set<Candidatura> findCandidatureByIdProgetto(Long idProgetto);

    Set<Candidatura> findCandidatureByIdProgettoAndStato(Long idProgetto, StatoCandidatura statoCandidatura);

    Candidatura findCandidaturaById(Long id);

    Candidatura findCandidaturaByIdProgettoAndIdProgettista(Long idProgetto, String idProgettista);
    
    //Set<Progettista> findProgettistiByIdsCandidatureInCandidature(Set<Long> idsCandidature);
    
}
