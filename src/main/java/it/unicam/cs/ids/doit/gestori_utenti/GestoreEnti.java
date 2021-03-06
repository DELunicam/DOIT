package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.utenti.Lavoratore;

@Service
public class GestoreEnti {

    @Autowired
    EnteRepository enteRepository;
    @Autowired
    LavoratoreRepository lavoratoreRepository;

    private GestoreEnti() {

    }

    public String getInfo(Long idEnte) {
        return enteRepository.findById(idEnte).get().toString();
    }

    public Ente getEnte(Long idEnte) {
        return enteRepository.findById(idEnte).get();
    }
    public Lavoratore getLavoratore(Long idLavoratore) {
        return lavoratoreRepository.findById(idLavoratore).get();
    }
    public boolean checkLavoratore(Long id, long idEnte)
    {
        boolean checkLavoratore = false;
        if(lavoratoreRepository.existsById(id))
        {
            Lavoratore one = getLavoratore(id);
            if(one.getIdEnte() == idEnte)
            {
                checkLavoratore = true;
            }
        }
        return checkLavoratore;
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

	public Ente creaEnte(String username, String password, String mail, String nome, String tipologia, String descrizione) {
        Ente ente = new Ente(username, BCrypt.hashpw(password, BCrypt.gensalt()), mail, nome, tipologia, descrizione);
        enteRepository.save(ente);
        return ente;
	}

}