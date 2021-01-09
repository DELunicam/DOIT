package it.unicam.cs.ids.valutazione;

import java.util.*;

import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.utils.FakeDb;

public class GestoreValutazioni {
    
    private FakeDb db = new FakeDb(); // fake db

    public GestoreValutazioni() {

    }

    // --> aggiunto idEsperto fra i parametri dei create -luca
    
    // crea valutazione positiva
    public Valutazione createValutazione(String idProgetto, String idEsperto, Map<Specializzazione, Integer> infoLavoratori) {
        Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto, infoLavoratori);
        this.db.addValutazione(nuovaValutazione);
        return nuovaValutazione;
    }

    // crea valutazione negativa
    public Valutazione createValutazione(String idProgetto, String idEsperto) {
        Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto);
        this.db.addValutazione(nuovaValutazione);
        return nuovaValutazione;
    }
    
}
