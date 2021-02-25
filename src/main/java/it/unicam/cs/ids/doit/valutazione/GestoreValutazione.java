package it.unicam.cs.ids.doit.valutazione;

import it.unicam.cs.ids.doit.gestori_utenti.EspertoRepository;
import it.unicam.cs.ids.doit.progetto.ProgettoRepository;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class GestoreValutazione {
    private static GestoreValutazione instance;

    @Autowired
    ValutazioneRepository repository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    EspertoRepository espertoRepository;


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

    public Valutazione findValutazioneById(Long idValutazione)
    {
        return repository.findById(idValutazione).get();
    }
    public Set<Valutazione> getVautazioniByIdProgetto(Long idProgetto)
    {
        return repository.findValutazioniByIdProgetto(idProgetto);
    }
}
