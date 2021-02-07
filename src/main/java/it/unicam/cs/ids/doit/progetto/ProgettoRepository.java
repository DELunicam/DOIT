package it.unicam.cs.ids.doit.progetto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgettoRepository extends JpaRepository<Progetto, Long> {

    List<Progetto> findAllByIdProponente(Long idProponente);

    List<Progetto> findAllByIdProponenteAndStatoProgetto(Long idProponente, StatoProgetto statoProgetto);

    List<Progetto> findAllByStatoProgetto(StatoProgetto statoProgetto);

}
