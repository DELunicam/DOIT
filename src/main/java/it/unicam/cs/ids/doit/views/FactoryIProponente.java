package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.utenti.Utente;

public class FactoryIProponente extends FactoryVista {

    @Override
    public void creaVista(Utente utente) {
        IProponente vista = new IProponente(utente.getId());
        vista.opzioniDisponibili();
    }
    
}
