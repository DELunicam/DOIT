package it.unicam.cs.ids.doit.candidatura;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    Set<Candidatura> findCandidatureByIdProgetto(Long idProgetto);

    Set<Candidatura> findCandidatureByIdProgettoAndStato(Long idProgetto, StatoCandidatura statoCandidatura);

    Set<Candidatura> findAllByIdProgettista(Long idProgettista);

    Candidatura findCandidaturaById(Long id);

    Candidatura findCandidaturaByIdProgettoAndIdProgettista(Long idProgetto, Long idProgettista);

    Set<Candidatura> findIdProgettiByIdProgettistaAndStato(Long idProgettista ,StatoCandidatura stato);
    
    Candidatura findCandidaturaByIdProgettoAndStato(Long idProgetto, StatoCandidatura stato);

    //Set<Progettista> findProgettistiByIdsCandidatureInCandidature(Set<Long> idsCandidature);
    
}
