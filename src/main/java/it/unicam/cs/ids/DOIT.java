package it.unicam.cs.ids;

import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.views.*;

import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("BENVENUTO IN DOIT, [PROP] PER L'INTERFACCIA PROPONENTE, [PROG] PER L'INTERFACCIA PROGETTISTA, [ES] PER L'INTERFACCIA ESPERTO, [VIS] PER L'INTERFACCIA VISITATORE \n");
        String input = sc.nextLine().toUpperCase();
        switch (input) {
            case "PROP": {
                String idProponente = digitaID(sc);
                IProponente proponente = new IProponente(idProponente);
                proponente.opzioniDisponibili();
                break;
            }
            case "PROG": {
                String idProgettista = digitaID(sc);
                IProgettista progettista = new IProgettista("PROG2");
                progettista.opzioniDisponibili();
                break;
            }
            case "ES": {
                String idEsperto = digitaID(sc);
                IEsperto iEsperto = new IEsperto("ESP1");
                iEsperto.opzioniDisponibili();
                break;
            }
            case "VIS": {
                IVisitatore iVisitatore = new IVisitatore();
                iVisitatore.opzioniDisponibili();
                break;
            }
        }

    }

    private static String digitaID (Scanner sc){
        System.out.println("DIGITA IL TUO ID");
        return sc.nextLine().toUpperCase();
    }
}
