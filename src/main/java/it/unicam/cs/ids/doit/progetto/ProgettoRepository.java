package it.unicam.cs.ids.doit.progetto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;

import java.util.Set;

@Repository
public interface ProgettoRepository extends JpaRepository<Progetto, Long> {


    Set<Progetto> findAllByIdProponente(Long idProponente);

    Set<Progetto> findAllByIdProponenteAndStatoProgetto(Long idProponente, StatoProgetto statoProgetto);

    Set<Progetto> findAllByIdProgettistaAndStatoCandidatura(Long idProgettista, StatoCandidatura stato);

    Set<Progetto> findAllByStatoProgetto(StatoProgetto statoProgetto);

    Set<Progetto> findProgettiByIdIn(Set<Long> id);

}
