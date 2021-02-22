package it.unicam.cs.ids.doit.associazione;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociazioneRepository extends JpaRepository<Associazione, Long> {
    
    public Set<Associazione> findAssociazioniByIdProgettistaAndStato(Long idProgettista, StatoAssociazione stato);

    public Associazione findAssociazioneByIdProgettista(Long idProgettista);
    
    public Set<Associazione> findAssociazioniByIdEnteAndIdProgettoAndStato(Long idEnte, Long idProgetto, StatoAssociazione stato);

    public Set<Associazione> findAssociazioniByIdEnteAndIdProgetto(Long idEnte, Long idProgetto);

    public Associazione findAssociazioneById(Long idAssociazione);

}
