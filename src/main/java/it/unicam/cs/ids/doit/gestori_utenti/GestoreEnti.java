package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.utenti.Lavoratore;

@Service
public class GestoreEnti {

    public static GestoreEnti instance;

    @Autowired
    EnteRepository enteRepository;
    @Autowired
    LavoratoreRepository lavoratoreRepository;

    public GestoreEnti() {

    }

    // Singleton
    public static GestoreEnti getInstance() {
        if (instance == null) {
            instance = new GestoreEnti();
        }
        return instance;
    }

    public String getInfo(Long idEnte) {
        return enteRepository.findById(idEnte).get().toString();
    }

    public Ente getEnte(Long idEnte) {
        return enteRepository.findById(idEnte).get();
    }

    public Set<Lavoratore> getLavoratori(Long idEnte) {
        return lavoratoreRepository.findLavoratoriByIdEnte(idEnte);
    }

    // usati id invece di oggetti
    public void assegnaProgetto(Long idLavoratore, Long idProgetto) {
        Lavoratore lavoratore = lavoratoreRepository.findById(idLavoratore).get();
        lavoratore.getIdProgettiSvolti().add(idProgetto);
        lavoratoreRepository.save(lavoratore);
    }

    public Set<Lavoratore> getLavoratori(Long idEnte, Specializzazione specializzazione) {
        return lavoratoreRepository.findLavoratoriByIdEnteAndSpecializzazioniIn(idEnte, Collections.singleton(specializzazione));
    }

}