package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.progetto.GestoreProgetto;
import it.unicam.cs.ids.doit.progetto.Progetto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ProgettoController {

    private final GestoreProgetto gestoreProgetto;

    ProgettoController(GestoreProgetto gestoreProgetto) {
        this.gestoreProgetto = gestoreProgetto;
    }


    @GetMapping(value="/progetti")
    List<Progetto> all(){
        return gestoreProgetto.findAllProgetti();
    }

    @GetMapping(value="/progetti/{id}")
    Progetto one(@PathVariable Long id){
        return gestoreProgetto.findProgetto(id);
    }


}
