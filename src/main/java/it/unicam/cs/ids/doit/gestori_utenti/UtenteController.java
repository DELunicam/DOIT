package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Utente;

@RestController
public class UtenteController {

    
    @Autowired
    GestoreUtenti gestoreUtenti;
    @Autowired
    GestoreProponenti gestoreProponenti;
    @Autowired
    GestoreProgettisti gestoreProgettisti;
    @Autowired
    GestoreEsperti gestoreEsperti;
    @Autowired
    GestoreEnti gestoreEnti;

    //PROPONENTE

    @PutMapping(value = "/utenti/proponente", params = {"username", "password", "mail", "nome", "cognome"})
    public void creaProponente(@RequestParam String username, @RequestParam String password,
        @RequestParam String mail, @RequestParam String nome, @RequestParam String cognome) {
        gestoreProponenti.creaProponente(username, password, mail, nome, cognome);
    }

    //PROGETTISTA

    @PutMapping(value = "/utenti/progettista", params = {"username", "password", "mail", "nome", "cognome", "specializzazioni"})
    public void creaProgettista(@RequestParam String username, @RequestParam String password,
        @RequestParam String mail, @RequestParam String nome, @RequestParam String cognome,
        @RequestParam Set<Specializzazione> specializzazioni) {
            gestoreProgettisti.creaProgettista(username, password, mail, nome, cognome, specializzazioni);
    }

    // ESPERTO

    @PutMapping(value = "/utenti/esperto", params = {"username", "password", "mail", "nome", "cognome", "specializzazioni"})
    public void creaEsperto(@RequestParam String username, @RequestParam String password,
        @RequestParam String mail, @RequestParam String nome, @RequestParam String cognome,
        @RequestParam Set<Specializzazione> specializzazioni) {
            gestoreEsperti.creaEsperto(username, password, mail, nome, cognome, specializzazioni);
    }

    // ENTE

    @PutMapping(value = "/utenti/esperto", params = {"username", "password", "mail", "nome", "tipologia", "descrizione"})
	public void creaEnte(@RequestParam String username, @RequestParam String password,
        @RequestParam String mail, @RequestParam String nome,@RequestParam String tipologia,
        @RequestParam String descrizione) {
            gestoreEnti.creaEnte(username, password, mail, nome,tipologia, descrizione);
	}

    // UTENTE

    @GetMapping(value = "/utenti/{username}")
    public boolean cercaUsername(@PathVariable String username) {
        return gestoreUtenti.cercaUsername(username);
    }


    @GetMapping
    public String getType(@PathVariable String username, @PathVariable String password) {
        return gestoreUtenti.getType(username, password);
    }

    @GetMapping(value= "/utente/{username}/{password}")
    public Utente getUtente(@PathVariable String username, @PathVariable String password) {
        return gestoreUtenti.getUtente(username, password);
    }

    @GetMapping(value = "/utenti")
    public Set<Utente> all() {
        return gestoreUtenti.all();
    }

}
