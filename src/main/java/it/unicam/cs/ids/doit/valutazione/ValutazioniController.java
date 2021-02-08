package it.unicam.cs.ids.doit.valutazione;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.doit.progetto.Specializzazione;

@RestController
public class ValutazioniController {

    private final GestoreValutazioni gestoreValutazioni;

    ValutazioniController(GestoreValutazioni gestoreValutazioni) {
        this.gestoreValutazioni = gestoreValutazioni;
    }

    @PostMapping(value="/valutazioni/positiva", params = {"idProgetto", "idEsperto"})
    Valutazione creaValutazionePositiva(@RequestParam Long idProgetto, @RequestParam Long idEsperto, @RequestBody Map<Specializzazione, Integer> infoLavoratori) {
        return gestoreValutazioni.createValutazione(idProgetto, idEsperto, infoLavoratori);
    }

    @PostMapping(value="/valutazioni/negativa", params = {"idProgetto", "idEsperto"})
    Valutazione createValutazioneNegativa(@RequestParam Long idProgetto, @RequestParam Long idEsperto) {
        return gestoreValutazioni.createValutazione(idProgetto, idEsperto);
    }
    
}
