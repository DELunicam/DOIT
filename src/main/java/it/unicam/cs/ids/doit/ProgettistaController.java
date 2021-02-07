package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.GestoriUtenti.GestoreProgettisti;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProgettistaController {
    private final GestoreProgettisti gestore;

    ProgettistaController(ProgettistaRepository repository, GestoreProgettisti gestore){
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
    
    @GetMapping(value="/progettisti", params = {"nome"})
    Progettista oneByNome(@RequestParam String nome){
        return gestore.getProgettistaByNome(nome);
    }

    @GetMapping(value="/progettisti", params = {"username"})
    Progettista oneByUsername(@RequestParam String username){
        return gestore.getProgettistaByUsername(username);
    }

 /*   @GetMapping(value= "specializzazioni/idProgettista/{idProgettista}")
    Set<Specializzazione> getSpecializzazioniByIdProgettista(@PathVariable Long idProgettista)
    {
        return gestore.getSpecializzazioni(idProgettista);
    }
   */
}
