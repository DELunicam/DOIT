package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.utenti.Lavoratore;

@RestController
public class EnteController {

    @Autowired
    GestoreEnti gestoreEnti;

    public EnteController() {
    }

    @GetMapping(value = "/enti/{id}/info")
    public String getInfoEnte(@PathVariable Long id) {
        return gestoreEnti.getInfo(id);
    }

    @GetMapping(value = "/enti/{id}")
    public Ente getEnte(@PathVariable Long id) {
        return gestoreEnti.getEnte(id);
    }
    @GetMapping(value = "/lavoratori/{idLavoratore}")
    public Lavoratore getLavoratore(@PathVariable Long idLavoratore) {
        return gestoreEnti.getLavoratore(idLavoratore);
    }
    @GetMapping(value = "/enti/{id}/lavoratori/{id}")
    public boolean checkLavoratori(@PathVariable Long idEnte, @PathVariable Long IdLavoratore)
    {
        return gestoreEnti.checkLavoratore(IdLavoratore, idEnte);
    }

    @GetMapping(value = "enti/{id}/lavoratori")
    public Set<Lavoratore> getLavoratori(@PathVariable Long id) {
        return gestoreEnti.getLavoratori(id);
    }

    @PutMapping(value = "lavoratori/{idLavoratore}")
    public void assegnaProgetto(@PathVariable Long idLavoratore, @RequestParam Long idProgetto) {
        gestoreEnti.assegnaProgetto(idLavoratore, idProgetto);
    }

    @GetMapping(value = "enti/{id}/lavoratori/{specializzazione}")
    public Set<Lavoratore> getLavoratori(@PathVariable Long id, @PathVariable Specializzazione specializzazione) {
        return gestoreEnti.getLavoratori(id, specializzazione);
    }

}
