package it.unicam.cs.ids.doit.views.factoryViews;

import it.unicam.cs.ids.doit.utenti.Utente;
import it.unicam.cs.ids.doit.views.IEsperto;

public class FactoryIEsperto extends FactoryVista {
    @Override
    public void creaVista(Utente utente) {
        IEsperto vista = new IEsperto(utente.getId());
        vista.opzioniDisponibili();
    }
}
