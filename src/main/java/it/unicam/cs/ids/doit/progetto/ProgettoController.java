package it.unicam.cs.ids.doit.progetto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import it.unicam.cs.ids.doit.utenti.Progettista;

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

    @GetMapping(value = "/progetti", params = {"specializzazioni", "statoProgetto"})
    public Set<Progetto> allByStatoProgettoAndSpecializzazione(@RequestParam Set<Specializzazione> specializzazioni, @RequestParam StatoProgetto statoProgetto) {
        return gestoreProgetto.getListaProgetti(specializzazioni, statoProgetto);
    }

    @PutMapping(value = "/progetti", params = {"idProgetto", "statoProgetto"})
    public void setStatoProgetto(@RequestParam Long idProgetto, @RequestParam StatoProgetto statoProgetto) {
        gestoreProgetto.modificaStatoProgetto(idProgetto, statoProgetto);
    }

    @PutMapping(value = "/progetti", params = {"statoProgetto"})
    public void setStatoProgetto(@RequestBody Progetto progetto, @RequestParam StatoProgetto statoProgetto) {
        gestoreProgetto.modificaStatoProgetto(progetto, statoProgetto);
    }

    @PutMapping(value = "/progetti/pubblica")
    public void pubblicaProgetto(@RequestBody Progetto progetto) {
        gestoreProgetto.pubblicaProgetto(progetto);
    }

    @PostMapping(value = "/progetti", params = {"idProponente", "nome", "descrizione"})
    public Progetto createProgetto(@RequestParam Long idProponente, @RequestParam String nome, @RequestParam String descrizione) {
        return gestoreProgetto.createProgetto(idProponente, nome, descrizione);
    }

    @PostMapping(value= "/progetti/{idProgetto}")
    public boolean checkIdProgetto(@PathVariable Long idProgetto){
        return gestoreProgetto.checkIdProgetto(idProgetto);
    }

    @GetMapping(value= "/progetti/{idProgetto}/{idProponente}/{stato}")
    public boolean checkProgetto(@PathVariable Long idProgetto, @PathVariable Long idProponente, @PathVariable StatoProgetto stato){
        return gestoreProgetto.checkProgetto(idProgetto, idProponente, stato);
    }

    @GetMapping(value = "/progetti", params ={"idProgetto", "statoProgetto"})
    public boolean checkStatoProgetto(@PathVariable Long idProgetto, @PathVariable StatoProgetto statoProgetto)
    {
        return gestoreProgetto.checkStatoProgetto(idProgetto, statoProgetto);
    }

    @GetMapping(value = "/progetti/{idProgetto}")
    public Progetto one(@PathVariable Long idProgetto) {
        return gestoreProgetto.getProgetto(idProgetto);
    }

    @PutMapping(value = "/progetti/{idProgetto}")
    void insertInfoProgettistiRichiesti(@PathVariable Long idProgetto, @RequestBody Map<Specializzazione, Integer> specializzazioni) {
        gestoreProgetto.insertInfoProgettisti(idProgetto, specializzazioni);
    }

    @PutMapping(value = "/progetti")
    public void insertInfoProgettistiRichiesti(@RequestBody Progetto progetto, @RequestBody Map<Specializzazione, Integer> specializzazioni) {
        gestoreProgetto.insertInfoProgettisti(progetto, specializzazioni);
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
