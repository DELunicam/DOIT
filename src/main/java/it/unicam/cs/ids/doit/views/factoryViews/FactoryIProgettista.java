package it.unicam.cs.ids.doit.views.factoryViews;

import it.unicam.cs.ids.doit.utenti.Utente;
import it.unicam.cs.ids.doit.views.IProgettista;

public class FactoryIProgettista extends FactoryVista {
    @Override
    public void creaVista(Utente utente) {
        IProgettista vista = new IProgettista(utente.getId());
        vista.opzioniDisponibili();
    }

}
