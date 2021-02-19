package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.notifica.Messaggio;
import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public class PrinterMessaggi {

    private static MessaggioController getMessaggioController() {
        return SpringContext.getBean(MessaggioController.class);
    }

    public static void printListaMessaggiNonLetti() {
        Set<Messaggio> messaggi = getMessaggioController().allByLetto(false);
        printBasicMessaggi(messaggi);
    }

    public static void printListaMessaggiLetti() {
        Set<Messaggio> messaggi = getMessaggioController().allByLetto(true);
        printBasicMessaggi(messaggi);
    }

    public static void printBasicMessaggi(Set<Messaggio> messaggi) {
        for (Messaggio msg : messaggi) {
            System.out.println("Mandato da utente n: " + msg.getIdSender());
            System.out.println("Ricevuta da utente n: " + msg.getIdReceiver());
            System.out.println("Messaggio: \n" +
                    msg.getTesto());
        }
    }
}
