package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.utenti.Lavoratore;

@RestController
public class EnteController {

    private final GestoreEnti gestoreEnti;

    EnteController(GestoreEnti gestoreEnti) {
        this.gestoreEnti = gestoreEnti;
    }

    @GetMapping(value = "/enti/{id}/info")
    String getInfoEnte(@PathVariable Long id) {
        return gestoreEnti.getInfo(id);
    }

    @GetMapping(value = "/enti/{id}")
    Ente getEnte(@PathVariable Long id) {
        return gestoreEnti.getEnte(id);
    }

    @GetMapping(value = "enti/{id}/lavoratori")
    Set<Lavoratore> getLavoratori(@PathVariable Long id) {
        return gestoreEnti.getLavoratori(id);
    }

    // TODO
    //@PostMapping
    //void assegnaProgetto() {
    //    gestoreEnti.assegnaProgetto(lavoratore, progetto);
    //}

    @GetMapping(value = "enti/{id}/lavoratori/{specializzazione}")
    Set<Lavoratore> getLavoratori(@PathVariable Long id, @PathVariable Specializzazione specializzazione) {
        return gestoreEnti.getLavoratori(id, specializzazione);
    }

}
