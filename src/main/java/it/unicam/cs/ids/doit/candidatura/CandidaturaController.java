package it.unicam.cs.ids.doit.candidatura;

import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class CandidaturaController {

    private final GestoreCandidature gestoreCandidature;

    CandidaturaController(GestoreCandidature gestoreCandidature) {
        this.gestoreCandidature = gestoreCandidature;
    }

    @GetMapping(value="/candidature", params = {"idProgetto"})
    Set<Candidatura> getCandidatureByIdProgetto(@RequestParam Long idProgetto) {
        return gestoreCandidature.getCandidature(idProgetto);
    }

    @GetMapping(value = "/candidature", params = {"idProgetto", "statoCandidatura"})
    Set<Candidatura> getCandidatureByIdProgettoAndStato(@RequestParam Long idProgetto, @RequestParam StatoCandidatura statoCandidatura) {
        return gestoreCandidature.getCandidature(idProgetto, statoCandidatura);
    }

    @GetMapping(value = "/candidature/{id}")
    Candidatura getCandidaturaById(@PathVariable Long id) {
        return gestoreCandidature.getCandidatura(id);
    }

    @GetMapping(value = "/candidature", params = {"idProgetto", "idProgettista"})
    Candidatura getCandidaturaByIdProgettoAndIdProgettista(@RequestParam Long idProgetto, @RequestParam Long idProgettista) {
        return gestoreCandidature.getCandidatura(idProgetto, idProgettista);
    }

    @PutMapping(value = "/candidature", params = {"id", "statoCandidatura"})
    void modificaStatoCandidatura(@RequestParam Long id, @RequestParam StatoCandidatura statoCandidatura) {
        gestoreCandidature.modificaStatoCandidatura(id, statoCandidatura);
    }

    @PutMapping(value = "/candidature", params = {"ids", "statoCandidatura"})
    void modificaStatoCandidature(@RequestParam Set<Long> ids, @RequestParam StatoCandidatura statoCandidatura) {
        gestoreCandidature.modificaStatoCandidature(statoCandidatura, ids);
    }

    /*
    @GetMapping(value="/progettisti/idsCandidature")
    Set<Progettista> getProgettistibyidsCandidatureInCandidature(@PathVariable Set<Long> idsCandidature) {
        //TODO
        return gestoreCandidature.getProgettisti(idsCandidature);
    }
*/
    @PostMapping(value = "/candidature", params = {"idProgettista", "idProgetto"})
    Candidatura creaCandidatura(@RequestParam Long idProgettista, @RequestParam Long idProgetto) {
        return gestoreCandidature.creaCandidatura(idProgettista, idProgetto);
    }

    @PostMapping(value="/candidature", params = {"idEsperto", "idsConsigliate", "idsSconsigliate"})
    void aggiungiPareriEsperto(@RequestParam Long idEsperto, @RequestParam Set<Long> idsConsigliate, @RequestParam Set<Long> idsSconsigliate) {
        gestoreCandidature.confermaSelezione(idEsperto, idsConsigliate, idsSconsigliate);

    }

}


    