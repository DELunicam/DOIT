package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.views.factoryViews.FactoryIVisitatore;

public class IUtente implements Vista {
    
    public void logout() {
        System.out.println("Ti sei scollegato con successo!");
        final FactoryIVisitatore factory = new FactoryIVisitatore();
        factory.creaVista();
    }

}
