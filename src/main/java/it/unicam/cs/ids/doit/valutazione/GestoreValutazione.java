package it.unicam.cs.ids.doit.valutazione;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class GestoreValutazione {
    private static GestoreValutazione instance;

    @Autowired
    ValutazioneRepository repository;

    public GestoreValutazione() {

    }

    // Singleton
    public static GestoreValutazione getInstance() {
        if (instance == null) {
            instance = new GestoreValutazione();
        }
        return instance;
    }
    
    // crea valutazione positiva
    public Valutazione createValutazione(Long idProgetto, Long idEsperto, Map<Specializzazione, Integer> infoLavoratori) {
        Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto, infoLavoratori);
        repository.save(nuovaValutazione);
        return nuovaValutazione;
    }

    // crea valutazione negativa
    public Valutazione createValutazione(Long idProgetto, Long idEsperto) {
        Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto);
        repository.save(nuovaValutazione);
        return nuovaValutazione;
    }
    
}
