package it.unicam.cs.ids.doit.progetto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import it.unicam.cs.ids.doit.utenti.Progettista;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ProgettoController {

    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    GestoreProgetto gestoreProgetto;
    public ProgettoController() {
    }
    //public ProgettoController(GestoreProgetto gestoreProgetto) {
    //    this.gestoreProgetto = gestoreProgetto;
    //}


    // GET /progetti

    @GetMapping(value = "/progetti")
    public Set<Progetto> all() {
        return gestoreProgetto.getListaProgetti();
    }

    @GetMapping(value = "/progetti", params = {"statoProgetto"})
    public Set<Progetto> allByStato(@RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(statoProgetto);
    }

    @GetMapping(value = "/progetti", params = {"idProponente"})
    public Set<Progetto> allByIdProponente(@RequestParam Long idProponente) {
        return gestoreProgetto.getListaProgetti(idProponente);
    }

    @GetMapping(value = "/progetti", params = {"idProponente", "statoProgetto"})
    public Set<Progetto> allByIdProponenteAndStato(@RequestParam Long idProponente, @RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(idProponente, statoProgetto);
    }

    //@GetMapping(value = "/progetti", params = {"specializzazione", "statoProgetto"})
    //List<Progetto> allByStatoProgettoAndSpecializzazione(@RequestParam Specializzazione specializzazione, @RequestParam StatoProgetto statoProgetto) {
    //    return gestoreProgetto.getListaProgetti(specializzazione, statoProgetto);
    //}

    @GetMapping(value = "/progetti", params = {"specializzazioni", "statoProgetto"})
    public Set<Progetto> allByStatoProgettoAndSpecializzazione(@RequestParam Set<Specializzazione> specializzazioni, @RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(specializzazioni, statoProgetto);
    }

    // PUT /progetti

    @PutMapping(value = "/progetti", params = {"idProgetto", "statoProgetto"})
    void setStatoProgetto(@RequestParam Long idProgetto, @RequestParam StatoProgetto statoProgetto) {
        gestoreProgetto.modificaStatoProgetto(idProgetto, statoProgetto);
    }


    // POST /progetti

    @PostMapping(value = "/progetti", params = {"idProponente", "nome", "descrizione"})
    void createProgetto(@RequestParam Long idProponente, @RequestParam String nome, @RequestParam String descrizione) {
        gestoreProgetto.createProgetto(idProponente, nome, descrizione);
    }


    // GET /progetti/{idProgetto}

    @GetMapping(value = "/progetti/{idProgetto}")
    public Progetto one(@PathVariable Long idProgetto) {
        return gestoreProgetto.getProgetto(idProgetto);
    }


    // PUT /progetti/{idProgetto}

    @PutMapping(value = "/progetti/{idProgetto}")
    void insertInfoProgettistiRichiesti(@PathVariable Long idProgetto, @RequestBody Map<Specializzazione, Integer> specializzazioni) {
        gestoreProgetto.insertInfoProgettisti(idProgetto, specializzazioni);
    }

    @GetMapping(value = "/progetti/svolti/{idProgettista}")
    public Set<Progetto> getListaProgettiSvolti(@PathVariable Long idProgettista) {
        return gestoreProgetto.getListaProgettiSvolti(idProgettista);
    }
    
    @GetMapping(value = "/progetti/svolti")
    public Set<Progetto> getListaProgettiSvolti(@RequestBody Progettista progettista) {
        return gestoreProgetto.getListaProgettiSvolti(progettista);
    }

    @GetMapping(value = "/progetti/{id}")
    public Set<Progetto> getProgettiCandidati(@PathVariable Set<Long> ids) {
        return gestoreProgetto.getProgettiCandidati(ids);
    }

}
