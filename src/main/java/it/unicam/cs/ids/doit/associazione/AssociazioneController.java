package it.unicam.cs.ids.doit.associazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AssociazioneController {

    @Autowired
    GestoreAssociazioni gestoreAssociazioni;

    public AssociazioneController() {
    }

    @GetMapping(value = "/associazioni", params = {"idProgettista", "stato"})
    public Set<Associazione> getAssociazioniByIdProgettistaAndStato(@RequestParam Long idProgettista, @RequestParam StatoAssociazione stato) {
        return gestoreAssociazioni.getAssociazioni(idProgettista, stato);
    }
    @GetMapping(value = "/associazioni", params = {"id"})
    // TODO passare id invece che associazione?
    public Associazione getAssociazioneById(@RequestParam Long idAssociazione) {
        return gestoreAssociazioni.getAssociazioneById(idAssociazione);
    }

    @GetMapping(value = "/associazioni", params = {"idProgettista"})
    // TODO passare id invece che associazione?
    Associazione getAssociazioneByIdProgettista(@RequestParam Long idProgettista) {
        return gestoreAssociazioni.getAssociazione(idProgettista);
    }

    @GetMapping(value = "/associazioni", params = {"idEnte", "idProgetto", "stato"})
    Set<Associazione> getAssociazioniByIdEnteAndIdProgettistaAndStato(@RequestParam Long idEnte, @RequestParam Long idProgetto, @RequestParam StatoAssociazione stato) {
        return gestoreAssociazioni.getAssociazioni(idEnte, idProgetto, stato);
    }

    @PutMapping(value = "/associazioni", params = {"stato"})
    public void modificaStatoAssociazione(@RequestBody Associazione associazione, @RequestParam StatoAssociazione stato) {
        gestoreAssociazioni.modificaStatoAssociazione(associazione, stato);
    }

    @GetMapping(value = "/associazioni/progettisti", params = {"idEnte", "idProgetto"})
    public  Set<Long> getIdProgettisti(@RequestParam Long idEnte, @RequestParam Long idProgetto) {
        return gestoreAssociazioni.getIdProgettisti(idEnte, idProgetto);
    }

    @PostMapping(value = "/associazioni", params = {"idEnte", "idProgettista", "idProgetto"})
    public Associazione creaAssociazione(@RequestParam Long idEnte, @RequestParam Long idProgettista, @RequestParam Long idProgetto) {
        return gestoreAssociazioni.creaAssociazione(idEnte, idProgettista, idProgetto);
    }

    
}
