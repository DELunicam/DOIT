package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.utenti.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UtenteController {

    
    @Autowired
    GestoreUtenti gestoreUtenti;
    @Autowired
    GestoreProponenti gestoreProponenti;
    @Autowired
    GestoreProgettisti gestoreProgettisti;

    @PutMapping(value = "/utenti/proponente")
    public void creaProponente(@RequestParam String username, @RequestParam String password,
        @RequestParam String mail, @RequestParam String nome, @RequestParam String cognome) {
        gestoreProponenti.creaProponente(username, password, mail, nome, cognome);
    }

    @GetMapping(value = "/utenti/{username}")
    public boolean cercaUsername(@PathVariable String username) {
        return gestoreUtenti.cercaUsername(username);
    }


    @GetMapping
    public String getType(@PathVariable String username, @PathVariable String password) {
        return gestoreUtenti.getType(username, password);
    }


    @GetMapping(value = "/utente/{username}/{password}")
    public Utente getUtente(@PathVariable String username, @PathVariable String password) {
        return gestoreUtenti.getUtente(username, password);
    }

    @GetMapping(value = "/utenti")
    public Set<Utente> all() {
        return gestoreUtenti.all();
    }

}
