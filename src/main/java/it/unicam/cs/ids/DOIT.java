package it.unicam.cs.ids;

import it.unicam.cs.ids.views.IProponente;

import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
<<<<<<< Updated upstream
        IProponente proponente = new IProponente();
        proponente.startProgetto();
=======

        IProponente proponente = new IProponente("PROP1");

        boolean exit = true;
        while(exit) {
            System.out.println("Cosa vuoi fare?\n" +
                    "[CREA]\n" +
                    "[PUBBLICA]\n" +
                    "[SELEZIONA PROGETTISTI]\n" +
                    "[CONFERMA PROGETTISTI]\n" +
                    "[EXIT]\n");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "CREA":
                    proponente.startProgetto();
                    break;
                case "PUBBLICA":
                    break; //
                case "SELEZIONA PROGETTISTI":
                    proponente.selezionaProgettisti();
                    break; //
                case "CONFERMA PROGETTISTI":
                    proponente.viewProgetti(StatoProgetto.NEUTRO);
                    break;//
                case "EXIT":
                    exit= false;
                    break;
            }
        }
>>>>>>> Stashed changes
    }
}
