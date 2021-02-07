package it.unicam.cs.ids.doit;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.GestoriUtenti.GestoreProgettisti;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ProgettistaController {
    private final ProgettistaRepository repository;
    private final GestoreProgettisti gestore;

    ProgettistaController(ProgettistaRepository repository, GestoreProgettisti gestore){
        this.repository = repository;
        this.gestore = gestore;
    }

    @GetMapping(value="/progettisti")
    Set<Progettista> all(){
        return gestore.getListaProgettisti();
    }
    

    @GetMapping(value="/progettisti/{id}")
    Progettista one(@PathVariable Long id){
        return gestore.getProgettista(id);
    }
    

    @GetMapping(value="/progettisti/nome/{nome}")
    Progettista oneByNome(@PathVariable String nome){
        return gestore.getProgettistaByNome(nome);
    }

    @GetMapping(value="/progettisti/username/{username}")
    Progettista oneByUsername(@PathVariable String username){
        return gestore.getProgettistaByUsername(username);
    }
 /*   @GetMapping(value= "specializzazioni/idProgettista/{idProgettista}")
    Set<Specializzazione> getSpecializzazioniByIdProgettista(@PathVariable Long idProgettista)
    {
        return gestore.getSpecializzazioni(idProgettista);
    }
   */
}
