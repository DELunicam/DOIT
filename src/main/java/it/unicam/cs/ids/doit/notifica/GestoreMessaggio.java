package it.unicam.cs.ids.doit.notifica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GestoreMessaggio {

    @Autowired
    MessaggioRepository messaggioRepository;

    public Set<Messaggio> allByLetto(boolean letto) {
        if (letto == true) {
            return messaggioRepository.findAllByLettoIsTrue();
        } else {
            return messaggioRepository.findAllByLettoIsFalse();

        }
    }


    public Messaggio createMessaggio(Long idSender, Long idReceiver, String testo) {
        return new Messaggio(idSender, idReceiver, testo);
    }
}
