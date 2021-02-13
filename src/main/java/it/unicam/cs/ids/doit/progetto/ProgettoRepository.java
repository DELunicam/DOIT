package it.unicam.cs.ids.doit.progetto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProgettoRepository extends JpaRepository<Progetto, Long> {


    Set<Progetto> findAllByIdProponente(Long idProponente);

    Set<Progetto> findAllByIdProponenteAndStatoProgetto(Long idProponente, StatoProgetto statoProgetto);

    Set<Progetto> findAllByStatoProgetto(StatoProgetto statoProgetto);

}
