package it.unicam.cs.ids.doit.valutazione;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class ValutazioneController {

    @Autowired
    GestoreValutazione gestoreValutazioni;


    public ValutazioneController() {
    }

    @GetMapping (value="/valutazioni/{id}")
    public Valutazione oneById(@PathVariable Long idValutazione)
    {
        return gestoreValutazioni.findValutazioneById(idValutazione);
    }
    @PostMapping(value="/valutazioni/positiva", params = {"idProgetto", "idEsperto"})
    public Valutazione creaValutazionePositiva(@RequestParam Long idProgetto, @RequestParam Long idEsperto, @RequestBody Map<Specializzazione, Integer> infoLavoratori) {
        return gestoreValutazioni.createValutazione(idProgetto, idEsperto, infoLavoratori);
    }

    @PostMapping(value="/valutazioni/negativa", params = {"idProgetto", "idEsperto"})
    public Valutazione creaValutazioneNegativa(@RequestParam Long idProgetto, @RequestParam Long idEsperto) {
        return gestoreValutazioni.createValutazione(idProgetto, idEsperto);
    }

    @GetMapping(value= "valutazioni/", params ={"idProgetto"})
    public Set<Valutazione> getValutzioniByIdProgetto(@PathVariable Long idProgetto)
    {
        return gestoreValutazioni.getVautazioniByIdProgetto(idProgetto);
    }    
}
