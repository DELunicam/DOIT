package it.unicam.cs.ids.doit.valutazione;

import it.unicam.cs.ids.doit.gestori_utenti.EspertoRepository;
import it.unicam.cs.ids.doit.progetto.ProgettoRepository;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GestoreValutazione {

    @Autowired
    ValutazioneRepository repository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    EspertoRepository espertoRepository;

    public GestoreValutazione() {
    }

    // crea valutazione positiva
    public Valutazione createValutazione(Long idProgetto, Long idEsperto, Map<Specializzazione, Integer> infoLavoratori) {
        if (espertoRepository.existsById(idEsperto) && progettoRepository.existsById(idProgetto)) {
            Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto, infoLavoratori);
            repository.save(nuovaValutazione);
            return nuovaValutazione;
        }
        return null;
    }

    // crea valutazione negativa
    public Valutazione createValutazione(Long idProgetto, Long idEsperto) {
        if (espertoRepository.existsById(idEsperto) && progettoRepository.existsById(idProgetto)) {
            Valutazione nuovaValutazione = new Valutazione(idProgetto, idEsperto);
            repository.save(nuovaValutazione);
            return nuovaValutazione;
        }
        return null;
    }

}
