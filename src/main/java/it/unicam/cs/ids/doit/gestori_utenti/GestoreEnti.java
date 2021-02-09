package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.doit.progetto.Progetto;
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

    // TODO
    // usare id invece di oggetti? metodo di gestoreAssociazioni?
    // crea associazione con stato ACCCETTATA_ENTE fra lavoratore dell'ente e progetto
    // modifica associazione con stato ACCCETTATA_ENTE progettista associato all'ente e progetto
    public void assegnaProgetto(Lavoratore lavoratore, Progetto progetto) {
        
    }

    // TODO metodo migliore?
    public Set<Lavoratore> getLavoratori(Long idEnte, Specializzazione specializzazione) {
        Set<Specializzazione> setDiUno = new HashSet<>();
        setDiUno.add(specializzazione);
        return lavoratoreRepository.findLavoratoriByIdEnteAndSpecializzazioniIn(idEnte, setDiUno);
    }

}