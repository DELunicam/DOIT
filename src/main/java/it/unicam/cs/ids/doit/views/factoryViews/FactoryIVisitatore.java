package it.unicam.cs.ids.doit.views.factoryViews;

import it.unicam.cs.ids.doit.views.IVisitatore;

public class FactoryIVisitatore {
    public void creaVista() {
        IVisitatore vista = new IVisitatore();
        vista.opzioniDisponibili();
    }
    
}
