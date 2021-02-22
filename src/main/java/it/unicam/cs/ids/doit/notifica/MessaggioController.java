package it.unicam.cs.ids.doit.notifica;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    @GetMapping(value = "/{idReceiver}/messaggi", params = {"letto"})
    public Set<Messaggio> allByLettoAndIdReceiver(@RequestParam boolean letto, @PathVariable Long idReceiver) {
        return gestoreMessaggio.allByLettoAndIdReceiver(letto, idReceiver);
    }

    @PostMapping(value = "/messaggi", params = {"idSender", "idReceiver", "testo"})
    public Optional<Messaggio> createMessaggio(@RequestParam Long idSender, @RequestParam Long idReceiver, @RequestParam String testo) {
        return gestoreMessaggio.createMessaggio(idSender, idReceiver, testo);
    }


}
