package it.unicam.cs.ids.doit.associazione;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestoreAssociazioni {

    @Autowired
    AssociazioneRepository repository;

    private GestoreAssociazioni() {

    }

    public Set<Associazione> getAssociazioni(Long idProgettista, StatoAssociazione stato) {
        return repository.findAssociazioniByIdProgettistaAndStato(idProgettista, stato);
    }

    public Associazione getAssociazione(Long idProgettista) {
        return repository.findAssociazioneByIdProgettista(idProgettista);
    }

    public Set<Associazione> getAssociazioni(Long idEnte, long idProgetto, StatoAssociazione  stato) {
        return repository.findAssociazioniByIdEnteAndIdProgettoAndStato(idEnte, idProgetto, stato);
    }

    public void modificaStatoAssociazione(Associazione associazione, StatoAssociazione stato) {
        associazione.setStatoAssociazione(stato);
        repository.save(associazione);
    }

    public Set<Long> getIdProgettisti(Long idEnte, Long idProgetto) {
        Set<Associazione> associazioni =  repository.findAssociazioniByIdEnteAndIdProgetto(idEnte, idProgetto);
        Set<Long> idsProgettisti = new HashSet<>();
        for (Associazione associazione : associazioni) {
            idsProgettisti.add(associazione.getIdProgettista());
        }
        return idsProgettisti;
    }

    public Associazione getAssociazioneById(Long idAssociazione)
    {
        if(repository.existsById(idAssociazione)){
        return repository.findAssociazioneById(idAssociazione);}
        return null;
    }

    public Associazione creaAssociazione(Long idEnte, Long idProgettista, Long idProgetto) {
        Associazione nuovaAssociazione = new Associazione(idEnte, idProgettista, idProgetto);
        repository.save(nuovaAssociazione);
        return nuovaAssociazione;
    }
    
}
