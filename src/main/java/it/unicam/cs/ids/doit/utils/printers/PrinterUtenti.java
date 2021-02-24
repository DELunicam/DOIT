package it.unicam.cs.ids.doit.utils.printers;

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
