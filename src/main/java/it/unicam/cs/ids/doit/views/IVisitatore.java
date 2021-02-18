package it.unicam.cs.ids.doit.views;

import java.util.Scanner;

public class IVisitatore {
    Scanner sc;

    public IVisitatore() {
        this.sc = new Scanner(System.in);
    }

    public void opzioniDisponibili() {
        while (true) {
            System.out.println("Cosa vuoi fare\n" +
                    "[VISUALIZZA PROGETTI]\n" +
                    "[VISUALIZZA PROGETTISTI]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "VISUALIZZA PROGETTI":
                    visualizzaListaProgetti();
                    break;
                case "VISUALIZZA PROGETTISTI":
                    visualizzaProgettisti();
                    break;
                case "EXIT":
                    return;
            }
        }
    }

    public void visualizzaListaProgetti() {
        PrinterProgetti.printListaProgetti();
        System.out.println("Digita l'id di un progetto per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgetto = sc.nextLine();
            if (idProgetto.equalsIgnoreCase("EXIT")) {
                break;
            }
            PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
        }
    }

    public void visualizzaProgettisti() {
        PrinterProgettisti.printListaProgettisti();
        System.out.println("Digita l'id di un progettista per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgettista = sc.nextLine();
            if (idProgettista.equalsIgnoreCase("EXIT")) {
                break;
            }
            PrinterProgettisti.printInfoProgettista(Long.valueOf(idProgettista));
            // TODO session?
            //PrinterProgetti.printListaProgettiSvolti(Long.valueOf(idProgettista));
        }
    }
}
