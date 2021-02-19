package it.unicam.cs.ids.doit.gestori_utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.doit.utenti.Proponente;

@Service
public class GestoreProponenti {
    @Autowired
    ProponenteRepository proponenteRepository;

    public Proponente creaProponente(String username, String password, String mail, String nome, String cognome) {
        Proponente proponente = new Proponente(username, BCrypt.hashpw(password, BCrypt.gensalt()), mail, nome, cognome);
        proponenteRepository.save(proponente);
        return proponente;
    }
    
}
