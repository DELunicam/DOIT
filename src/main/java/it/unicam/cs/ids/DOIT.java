package it.unicam.cs.ids;

import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.views.IProgettista;
import it.unicam.cs.ids.views.IProponente;
import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("BENVENUTO IN DOIT,[PROP] PER L'INTERFACCIA PROPONENTE, [PROG] PER L'INTERFACCIA PROGETTISTA, [ES] PER L'INTERFACIA ESPERTO \n");
        String input = sc.nextLine().toUpperCase();
        switch(input)
        {
            case "PROP":
            {
                System.out.println("DIGITA IL TUO ID");
                input = sc.nextLine().toUpperCase();
                IProponente proponente = new IProponente(input);
                while(!exit) {
                    System.out.println("Cosa vuoi fare?\n" +
                            "[CREA]\n" +
                            "[PUBBLICA]\n" +
                            "[SELEZIONA PROGETTISTI]\n" +
                            "[CONFERMA PROGETTISTI]\n" +
                            "[EXIT]");
                    input = sc.nextLine().toUpperCase();
                    switch (input) {
                        case "CREA":
                            proponente.startProgetto();
                            break;
                        case "PUBBLICA":
                            proponente.viewProgetti(StatoProgetto.NEUTRO);
                            break;
                        case "SELEZIONA PROGETTISTI":
                            proponente.viewProgetti(StatoProgetto.PUBBLICO);
                            break;
                        case "CONFERMA PROGETTISTI":
                            proponente.viewProgetti(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
                            break;
                        case "EXIT":
                            exit=true;
                            break;
                               }
                        
                     }
            }
            case "PROG":
            {
                System.out.println("DIGITA IL TUO ID");
                input = sc.nextLine().toUpperCase();
                IProgettista progettista = new IProgettista("PROG2");
                while(!exit) {
                    System.out.println("Cosa vuoi fare?\n" +
                            "[CANDIDA]\n" +
                            "[EXIT]");
                    input = sc.nextLine().toUpperCase();
                    switch (input) {
                        case "CANDIDA":
                            progettista.candida();
                            break;
                         case "EXIT" :
                         exit = true;
                         break;   
                    
                               }
                        
                     }
            }
            case "ES":
            {
                System.out.println("DIGITA IL TUO ID");
                input = sc.nextLine().toUpperCase();
                //TODO IESPERTO
            }
        }
        
    }
}
