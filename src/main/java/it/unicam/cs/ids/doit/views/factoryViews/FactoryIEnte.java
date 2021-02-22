package it.unicam.cs.ids.doit.views.factoryViews;

import it.unicam.cs.ids.doit.utenti.Utente;
import it.unicam.cs.ids.doit.views.IEnte;

public class FactoryIEnte extends FactoryVista {
    @Override
    public void creaVista(Utente utente) {
        IEnte vista = new IEnte(utente.getId());
        vista.opzioniDisponibili();
    }
}
