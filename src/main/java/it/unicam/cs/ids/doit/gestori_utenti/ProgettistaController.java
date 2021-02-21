package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProgettistaController {

    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    GestoreProgettisti gestoreProgettisti;

    public ProgettistaController() {
    }

    @GetMapping(value="/progettisti")
    public Set<Progettista> all(){
        return gestoreProgettisti.getListaProgettisti();
    }

    @GetMapping(value = "/progettisti/{id}")
    public Progettista one(@PathVariable Long id) {
        return gestoreProgettisti.getProgettista(id);
    }

    @GetMapping(value = "/progettisti", params = {"nome"})
    Progettista oneByNome(@RequestParam String nome) {
        return gestoreProgettisti.getProgettistaByNome(nome);
    }

    @GetMapping(value = "/progettisti", params = {"username"})
    public Progettista oneByUsername(@RequestParam String username) {
        return gestoreProgettisti.getProgettistaByUsername(username);
    }
    @GetMapping(value = "/progettisti/{id}")
    Set<Progettista> getProgettiCandidati(@PathVariable Set<Long> id)
    {
        return gestore.getListaProgettistiById(id);

    }

    @GetMapping(value= "specializzazioni/idProgettista/{idProgettista}")
    public Set<Specializzazione> getSpecializzazioniByIdProgettista(@PathVariable Long idProgettista) {
        return gestoreProgettisti.getSpecializzazioni(idProgettista);
    }
}
