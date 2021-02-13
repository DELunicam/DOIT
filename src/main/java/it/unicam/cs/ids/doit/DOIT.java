package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.views.IEsperto;
import it.unicam.cs.ids.doit.views.IProgettista;
import it.unicam.cs.ids.doit.views.IProponente;
import it.unicam.cs.ids.doit.views.IVisitatore;
import it.unicam.cs.ids.doit.views.IEnte;

import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class DOIT {

    //public static void main(String[] args) {
    //@RequestMapping("/")
    public static void runApp() {
       
        Scanner sc = new Scanner(System.in);
        System.out.println("BENVENUTO IN DOIT, [PROP] PER L'INTERFACCIA PROPONENTE, [PROG] PER L'INTERFACCIA PROGETTISTA, [ES] PER L'INTERFACCIA ESPERTO, [VIS] PER L'INTERFACCIA VISITATORE \n");
        String input = sc.nextLine().toUpperCase();
        switch (input) {
            case "PROP": {
                String idProponente = digitaID(sc);
                IProponente proponente = new IProponente(2L);
                proponente.opzioniDisponibili();
                break;
            }
            case "PROG": {
                String idProgettista = digitaID(sc);
                IProgettista progettista = new IProgettista(7L);
                progettista.opzioniDisponibili();
                break;
            }
            case "ES": {
                String idEsperto = digitaID(sc);
                IEsperto iEsperto = new IEsperto(3L);
                iEsperto.opzioniDisponibili();
                break;
            }
            case "VIS": {
                IVisitatore iVisitatore = new IVisitatore();
                iVisitatore.opzioniDisponibili();
                break;
            }
            case "ENTE":
            {
                String idEnte = digitaID(sc);
                IEnte iEnte = new IEnte(1L);
                iEnte.opzioniDisponibili();
            }
        }

    }

    private static String digitaID (Scanner sc){
        System.out.println("DIGITA IL TUO ID");
        return sc.nextLine().toUpperCase();
    }
}
