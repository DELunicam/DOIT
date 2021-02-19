package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Esperto;

@Service
public class GestoreEsperti {

    @Autowired
    EspertoRepository espertoRepository;

	public Esperto creaEsperto(String username, String password, String mail, String nome, String cognome, Set<Specializzazione> specializzazioni) {
        Esperto esperto = new Esperto(username, password, mail, nome, cognome, specializzazioni);
        espertoRepository.save(esperto);
        return esperto;
	}

}
