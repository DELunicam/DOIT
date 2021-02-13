package it.unicam.cs.ids.doit.views;

import java.util.Scanner;
import java.util.Set;

import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.progetto.Progetto;

public class IVisitatore {
    Scanner sc;

    private ProgettoController getProgettoController() {
        return SpringContext.getBean(ProgettoController.class);
    }
    private ProgettistaController getProgettistaController() {
        return SpringContext.getBean(ProgettistaController.class);
    }

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
        Set<Progetto> progetti = getProgettoController().all();
        PrinterProgetti.printBasicProgetti(progetti);
        System.out.println("Digita l'id di un progetto per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgetto = sc.nextLine();
            if (idProgetto.equalsIgnoreCase("EXIT")) {
                break;
            }
            Progetto progetto = getProgettoController().one(Long.valueOf(idProgetto));
            PrinterProgetti.printInfoProgetto(progetto);
        }
    }

    public void visualizzaProgettisti() {
        Set<Progettista> progettisti = getProgettistaController().all();
        PrinterProgettisti.printBasicProgettisti(progettisti);
        System.out.println("Digita l'id di un progettista per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgettista = sc.nextLine();
            if (idProgettista.equalsIgnoreCase("EXIT")) {
                break;
            }
            Progettista progettista = getProgettistaController().one(Long.valueOf(idProgettista));
            PrinterProgettisti.printInfoProgettista(progettista);
            //TODO progetti svolti?
            //Set<Progetto> progettiSvolti = getProgettoController().getListaProgettiSvolti(Long.valueOf(idProgettista));
            //PrinterProgetti.printBasicProgetti(progettiSvolti);
        }
    }
}
