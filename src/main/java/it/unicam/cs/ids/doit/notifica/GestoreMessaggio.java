package it.unicam.cs.ids.doit.notifica;

import it.unicam.cs.ids.doit.gestori_utenti.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GestoreMessaggio {

    @Autowired
    MessaggioRepository messaggioRepository;

    @Autowired
    UtenteRepository utenteRepository;

    public Set<Messaggio> allByLetto(boolean letto) {
        if (letto) {
            return messaggioRepository.findAllByLettoIsTrue();
        } else {
            return messaggioRepository.findAllByLettoIsFalse();
        }
    }

    public Set<Messaggio> allByLettoAndIdReceiver(boolean letto, Long idReceiver) {
        if (letto) {
            return messaggioRepository.findAllByLettoIsTrueAndIdReceiver(idReceiver);
        } else {
            return messaggioRepository.findAllByLettoIsFalseAndIdReceiver(idReceiver);
        }
    }


    public Optional<Messaggio> createMessaggio(Long idSender, Long idReceiver, String testo) {
        if (utenteRepository.existsById(idReceiver)) {
            Messaggio msg = new Messaggio(idSender, idReceiver, testo);
            messaggioRepository.save(msg);
            return Optional.of(msg);
        }
        return Optional.empty();
    }
}
