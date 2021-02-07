package it.unicam.cs.ids.doit.valutazione;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GestoreValutazioni {
    private static GestoreValutazioni instance;
//    private FakeDb db = new FakeDb(); // fake db

    public GestoreValutazioni() {

    }

    // Singleton
    public static GestoreValutazioni getInstance() {
        if (instance == null) {
            instance = new GestoreValutazioni();
        }
        return instance;
    }

    // --> aggiunto idEsperto fra i parametri dei create -luca
    
    // crea valutazione positiva
    public Valutazione createValutazione(Long idProgetto, String idEsperto, Map<Specializzazione, Integer> infoLavoratori) {
        Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto, infoLavoratori);
//        this.db.addValutazione(nuovaValutazione);
        return nuovaValutazione;
    }

    // crea valutazione negativa
    public Valutazione createValutazione(Long idProgetto, String idEsperto) {
        Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto);
//        this.db.addValutazione(nuovaValutazione);
        return nuovaValutazione;
    }
    
}
