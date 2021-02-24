<<<<<<< HEAD:src/main/java/it/unicam/cs/ids/doit/utils/printers/PrinterUtenti.java
package it.unicam.cs.ids.doit.utils.printers;
=======
package it.unicam.cs.ids.doit.views.printers;
>>>>>>> enzo:src/main/java/it/unicam/cs/ids/doit/views/printers/PrinterUtenti.java

import it.unicam.cs.ids.doit.gestori_utenti.UtenteController;
import it.unicam.cs.ids.doit.utenti.Utente;
import it.unicam.cs.ids.doit.utils.SpringContext;

public abstract class PrinterUtenti {
    private static UtenteController getUtenteController() {
        return SpringContext.getBean(UtenteController.class);
    }

    public static void printListaUtenti() {
        System.out.println("ID, USERNAME, EMAIL");
        for (Utente u : getUtenteController().all()) {
            System.out.println(u.getId() + " " + u.getUsername() + ", " + u.getMailAddress());

        }
    }
}
