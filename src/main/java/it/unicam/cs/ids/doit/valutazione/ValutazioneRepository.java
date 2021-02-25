package it.unicam.cs.ids.doit.valutazione;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;
public interface ValutazioneRepository extends JpaRepository<Valutazione, Long> {
    
    Set<Valutazione> findValutazioniByIdProgetto(Long idProgetto);
}
