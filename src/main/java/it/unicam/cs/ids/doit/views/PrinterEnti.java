package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.gestori_utenti.EnteController;
import it.unicam.cs.ids.doit.utenti.Ente;



import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterEnti {
    private static EnteController getEnteController() {
        return SpringContext.getBean(EnteController.class);
    }

    public static void printInfoEnte(Long idEnte) {
    System.out.println(getEnteController().getInfoEnte(idEnte));
    }
    
}