package it.unicam.cs.ids.doit.gestori_utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.doit.utenti.Utente;

@Service
public class GestoreUtenti {
    @Autowired
    UtenteRepository utenteRepository;

    public boolean cercaUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }

    public Utente getUtente(String username, String password) {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente != null) {
            String dbPw = utente.getPassword();
            if (BCrypt.checkpw(password, dbPw)) {
                return utente;
            }
        }
        return null;
    }

    public String getType(String username, String password) {
        return utenteRepository.findDtypeByUsernameAndPassword(username, password);

    }
    
}
