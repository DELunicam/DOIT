package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.progetto.GestoreProgetto;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
class ProgettoController {

    private final GestoreProgetto gestoreProgetto;

    ProgettoController(GestoreProgetto gestoreProgetto) {
        this.gestoreProgetto = gestoreProgetto;
    }


    // GET /progetti

    @GetMapping(value = "/progetti")
    List<Progetto> all() {
        return gestoreProgetto.getListaProgetti();
    }

    @GetMapping(value = "/progetti", params = {"statoProgetto"})
    List<Progetto> allByStato(@RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(statoProgetto);
    }

    @GetMapping(value = "/progetti", params = {"idProponente"})
    List<Progetto> allByIdProponente(@RequestParam Long idProponente) {
        return gestoreProgetto.getListaProgetti(idProponente);
    }

    @GetMapping(value = "/progetti", params = {"idProponente", "statoProgetto"})
    List<Progetto> allByIdProponenteAndStato(@RequestParam Long idProponente, @RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(idProponente, statoProgetto);
    }

    @GetMapping(value = "/progetti", params = {"specializzazione", "statoProgetto"})
    List<Progetto> allByStatoProgettoAndSpecializzazione(@RequestParam Specializzazione specializzazione, @RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(specializzazione, statoProgetto);
    }

    @GetMapping(value = "/progetti", params = {"specializzazioni", "statoProgetto"})
    List<Progetto> allByStatoProgettoAndSpecializzazione(@RequestParam Set<Specializzazione> specializzazioni, @RequestParam StatoProgetto statoProgetto) {
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
    Progetto one(@PathVariable Long idProgetto) {
        return gestoreProgetto.getProgetto(idProgetto);
    }


    // PUT /progetti/{idProgetto}

    @PutMapping(value = "/progetti/{idProgetto}")
    void insertInfoProgettistiRichiesti2(@PathVariable Long idProgetto, @RequestBody Map<Specializzazione, Integer> specializzazioni) {
        gestoreProgetto.insertInfoProgettisti(idProgetto, specializzazioni);
    }

}
