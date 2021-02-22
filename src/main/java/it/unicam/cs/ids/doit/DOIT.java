package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.views.factoryViews.FactoryIVisitatore;
import org.springframework.stereotype.Component;

@Component
public class DOIT {

    public static void startApp() {
        System.out.println("BENVENUTO IN DOIT!");
        final FactoryIVisitatore factory = new FactoryIVisitatore();
        factory.creaVista();
    }

}
