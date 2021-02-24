<<<<<<< HEAD:src/main/java/it/unicam/cs/ids/doit/utils/printers/PrinterMessaggi.java
package it.unicam.cs.ids.doit.utils.printers;
=======
package it.unicam.cs.ids.doit.views.printers;
>>>>>>> enzo:src/main/java/it/unicam/cs/ids/doit/views/printers/PrinterMessaggi.java

import it.unicam.cs.ids.doit.notifica.Messaggio;
import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterMessaggi {

    private static MessaggioController getMessaggioController() {
        return SpringContext.getBean(MessaggioController.class);
    }

    public static void printListaMessaggiNonLetti(Long idReceiver) {
        Set<Messaggio> messaggi = getMessaggioController().allByLettoAndIdReceiver(false, idReceiver);
        if (messaggi.size() == 0) {
            System.out.println("Non ci sono nuovi messaggi");
        }
        printBasicMessaggi(messaggi);
    }

    public static void printListaMessaggiLetti(Long idReceiver) {
        Set<Messaggio> messaggi = getMessaggioController().allByLettoAndIdReceiver(true, idReceiver);
        if (messaggi.size() == 0) {
            System.out.println("Non ci sono messaggi");
        }
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
