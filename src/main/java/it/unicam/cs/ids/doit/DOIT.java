package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.views.*;
import it.unicam.cs.ids.doit.views.factoryViews.FactoryIVisitatore;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

@Controller
public class DOIT {

    private static ProgettistaController getProgettistaController() {
        return SpringContext.getBean(ProgettistaController.class);
    }

    public static void startApp() {
        System.out.println("BENVENUTO IN DOIT!");
        final FactoryIVisitatore factory = new FactoryIVisitatore();
        factory.creaVista();
        //IVisitatore visitatore = new IVisitatore();
		//visitatore.opzioniDisponibili();
    }

    public static void runApp() {

        Scanner sc = new Scanner(System.in);
        System.out.println("BENVENUTO IN DOIT, [PROP] PER L'INTERFACCIA PROPONENTE, [PROG] PER L'INTERFACCIA PROGETTISTA, [ES] PER L'INTERFACCIA ESPERTO, [VIS] PER L'INTERFACCIA VISITATORE \n");
        String input = sc.nextLine().toUpperCase();
        switch (input) {
            case "PROP": {
                Long idProponente = digitaID(sc);
                IProponente proponente = new IProponente(2L);
                proponente.opzioniDisponibili();
                break;
            }
            case "PROG": {
                Long idProgettista = digitaID(sc);
                IProgettista progettista = new IProgettista(idProgettista);
                progettista.opzioniDisponibili();
                break;
            }
            case "ES": {
                Long idEsperto = digitaID(sc);
                IEsperto iEsperto = new IEsperto(3L);
                iEsperto.opzioniDisponibili();
                break;
            }
            case "VIS": {
                IVisitatore iVisitatore = new IVisitatore();
                iVisitatore.opzioniDisponibili();
                break;
            }
            case "ENTE": {
                Long idEnte = digitaID(sc);
                IEnte iEnte = new IEnte(1L);
                iEnte.opzioniDisponibili();
            }
        }

    }

    private static Long digitaID(Scanner sc) {
        System.out.println("DIGITA IL TUO USERNAME");
        String username = sc.nextLine();
        Progettista prog = getProgettistaController().oneByUsername(username);
        return prog.getId();
    }
}
