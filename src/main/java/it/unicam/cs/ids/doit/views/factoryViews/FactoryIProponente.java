package it.unicam.cs.ids.doit.views.factoryViews;

import it.unicam.cs.ids.doit.utenti.Utente;
import it.unicam.cs.ids.doit.views.IProponente;

public class FactoryIProponente extends FactoryVista {

    @Override
    public void creaVista(Utente utente) {
        IProponente vista = new IProponente(utente.getId());
        vista.opzioniDisponibili();
    }
    
}
