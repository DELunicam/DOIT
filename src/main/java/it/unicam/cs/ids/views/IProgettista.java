package it.unicam.cs.ids.views;

import it.unicam.cs.ids.GestoriUtenti.GestoreProgettisti;
import it.unicam.cs.ids.candidatura.GestoreCandidature;
import it.unicam.cs.ids.progetto.StatoProgetto;

import java.util.Scanner;


public class IProgettista {
    Scanner sc;
    GestoreProgettisti gestoreProgetto = GestoreProgettisti.getInstance();
    GestoreCandidature gestoreCandidature = GestoreCandidature.getInstance();
    String idProgettista;


    public IProgettista(String idProgettista) {
        this.sc = new Scanner(System.in);
        this.idProgettista = idProgettista;
    }

    public void candida() {
        System.out.println("Vuoi visualizzare i progetti a cui puoi candidarti? \n[Y] YES,    [N] NO)\n");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            viewProgettiCandidabili();

        } else if (input.equals("N")) {
            System.out.println("Ok, operazione annullata \n");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    public void viewProgettiCandidabili() {
        System.out.println("Puoi candidarti ai seguenti progetti \n");
        PrinterProgetti.printListaProgetti(gestoreProgetto.getSpecializzazioni(idProgettista), StatoProgetto.PUBBLICO);
        selezionaProgetto();
    }

    public void selezionaProgetto() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            PrinterProgetti.printInfoProgetto(idProgetto);
            System.out.println("Desideri candidarti a questo progetto?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                gestoreCandidature.creaCandidatura(idProgettista, idProgetto);
                System.out.println("Congratulazioni, ti sei candidato al progetto " + idProgetto);

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                viewProgettiCandidabili();
            } else {
                System.out.println("Impossibile processare l'operazione");
            }

        }
    }


}
