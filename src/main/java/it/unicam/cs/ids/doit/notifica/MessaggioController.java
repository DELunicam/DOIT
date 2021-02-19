package it.unicam.cs.ids.doit.notifica;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MessaggioController {
    @Autowired
    MessaggioRepository notificaRepository;
    @Autowired
    GestoreMessaggio gestoreMessaggio;

    public MessaggioController() {
    }

    @GetMapping(value = "/messaggi", params = {"letto"})
    public Set<Messaggio> allByLetto(@RequestParam boolean letto) {
        return gestoreMessaggio.allByLetto(letto);
    }

    @PostMapping(value = "/messaggi", params = {"idSender", "idReceiver", "testo"})
    public Messaggio createMessaggio(@RequestParam Long idSender, @RequestParam Long idReceiver, @RequestParam String testo) {
        return gestoreMessaggio.createMessaggio(idSender, idReceiver, testo);
    }


}
