package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProgettistaController {
    private final ProgettistaRepository repository;

    ProgettistaController(ProgettistaRepository repository){
        this.repository = repository;
    }

    @GetMapping(value="/progettisti")
    List<Progettista> all(){
        return repository.findAll();
    }

    @GetMapping(value="/progettisti/{id}")
    Progettista one(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @GetMapping(value="/progettisti/nome/{nome}")
    Progettista oneByNome(@PathVariable String nome){
        return repository.findProgettistaByNome(nome);
    }

    @GetMapping(value="/progettisti/username/{username}")
    Progettista oneByUsername(@PathVariable String username){
        return repository.findProgettistaByUsername(username);
    }
}
