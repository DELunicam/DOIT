package it.unicam.cs.ids.doit.candidatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class CandidaturaController {

    @Autowired
    GestoreCandidature gestoreCandidature;

    public CandidaturaController() {
    }

    @GetMapping(value="/candidature", params = {"idProgetto"})
    Set<Candidatura> getCandidatureByIdProgetto(@RequestParam Long idProgetto) {
        return gestoreCandidature.getCandidature(idProgetto);
    }

    @GetMapping(value = "/candidature", params = {"idProgetto", "statoCandidatura"})
    public Set<Candidatura> getCandidatureByIdProgettoAndStato(@RequestParam Long idProgetto, @RequestParam StatoCandidatura statoCandidatura) {
        return gestoreCandidature.getCandidature(idProgetto, statoCandidatura);
    }

    @GetMapping(value = "/candidature/{id}")
    public Candidatura getCandidaturaById(@PathVariable Long id) {
        return gestoreCandidature.getCandidatura(id);
    }

    @GetMapping(value = "/candidature", params = {"idProgetto", "idProgettista"})
    public Candidatura getCandidaturaByIdProgettoAndIdProgettista(@RequestParam Long idProgetto, @RequestParam Long idProgettista) {
        return gestoreCandidature.getCandidatura(idProgetto, idProgettista);
    }

    @PutMapping(value = "/candidature", params = {"id", "statoCandidatura"})
    public void modificaStatoCandidatura(@RequestParam Long id, @RequestParam StatoCandidatura statoCandidatura) {
        gestoreCandidature.modificaStatoCandidatura(id, statoCandidatura);
    }

    @PutMapping(value = "/candidature", params = {"ids", "statoCandidatura"})
    public void modificaStatoCandidature(@RequestParam Set<Long> ids, @RequestParam StatoCandidatura statoCandidatura) {
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
    public Candidatura creaCandidatura(@RequestParam Long idProgettista, @RequestParam Long idProgetto) {
        return gestoreCandidature.creaCandidatura(idProgettista, idProgetto);
    }

    @PostMapping(value = "/candidature", params = {"id"})
    public void aggiungiCandidatura(@RequestParam Long id, @RequestBody Set<Long> idsCandidature) {
        gestoreCandidature.addCandidatura(id, idsCandidature);
    }

    @PostMapping(value="/candidature", params = {"idEsperto", "idsConsigliate", "idsSconsigliate"})
    public void aggiungiPareriEsperto(@RequestParam Long idEsperto, @RequestParam Set<Long> idsConsigliate, @RequestParam Set<Long> idsSconsigliate) {
        gestoreCandidature.confermaSelezione(idEsperto, idsConsigliate, idsSconsigliate);

    }

    @GetMapping(value ="/candidature/{idProgettista}/candidature/{stato}")
    public Set<Long> getIdProgetti(@PathVariable Long idProgettista, @PathVariable StatoCandidatura stato){
        return gestoreCandidature.getIdProgetti(idProgettista, stato);
    }

}


    