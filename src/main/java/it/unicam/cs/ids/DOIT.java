package it.unicam.cs.ids;

import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utils.GestoreProgetto;
import it.unicam.cs.ids.views.IProponente;

import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        IProponente proponente = new IProponente("PROP1");


        while(true) {
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
                case "PUBBLICA":
                    ; //
                case "SELEZIONA PROGETTISTI":
                    ; //
                case "CONFERMA PROGETTISTI":
                    proponente.viewProgetti(StatoProgetto.NEUTRO); //
                case "EXIT": break;
            }
        }
    }
}
