package it.unicam.cs.ids.doit.candidatura;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CandidatureRepository extends JpaRepository<Candidatura, String> {

    Set<Candidatura> findCandidatureByIdProgetto(Long idProgetto);

    Set<Candidatura> findCandidatureByIdProgettoAndStato(Long idProgetto, StatoCandidatura statoCandidatura);

    Set<Candidatura> findAllByIdProgettista(Long idProgettista);

    Candidatura findCandidaturaById(Long id);

    Candidatura findCandidaturaByIdProgettoAndIdProgettista(Long idProgetto, Long idProgettista);


    //Set<Progettista> findProgettistiByIdsCandidatureInCandidature(Set<Long> idsCandidature);

}
