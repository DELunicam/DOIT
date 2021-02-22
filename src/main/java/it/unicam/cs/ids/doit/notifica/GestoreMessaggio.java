package it.unicam.cs.ids.doit.notifica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GestoreMessaggio {

    @Autowired
    MessaggioRepository messaggioRepository;

    @Autowired
    MessaggioRepository utenteRepository;

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
            return Optional.of(new Messaggio(idSender, idReceiver, testo));
        }
        return Optional.empty();
    }
}
