package it.unicam.cs.ids;

import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.views.IProponente;

import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        IProponente proponente = new IProponente("PROP1");

        boolean exit = false;
        while(!exit) {
            System.out.println("Cosa vuoi fare?\n" +
                    "[CREA]\n" +
                    "[PUBBLICA]\n" +
                    "[SELEZIONA PROGETTISTI]\n" +
                    "[CONFERMA PROGETTISTI]\n" +
                    "[EXIT]");
            String input = sc.nextLine().toUpperCase();
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
}
