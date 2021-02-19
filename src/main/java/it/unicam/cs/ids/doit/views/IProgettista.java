package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utils.SpringContext;


public class IProgettista extends IUtente {

    private ProgettistaController getProgettistaController() {
        return SpringContext.getBean(ProgettistaController.class);
    }

    private CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }


    public IProgettista(Long idProgettista) {
        super(idProgettista);
    }

    public void opzioniDisponibili() {
        while (true) {
            System.out.println("Cosa vuoi fare?\n" +
                    "[CANDIDA]\n" +
                    "[INVIA MESSAGGIO]\n" +
                    "[VISUALIZZA NOTIFICHE]\n" +
                    "[EXIT]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "CANDIDA":
                    candida();
                    break;
                case "INVIA MESSAGGIO":
                    inviaMessaggio();
                    break;
                case "VISUALIZZA NOTIFICHE":
                    visualizzaMessaggi();
                    break;
                case "EXIT":
                    return;

            }
        }
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
        PrinterProgetti.printListaProgetti(getProgettistaController().getSpecializzazioniByIdProgettista(idUtente), StatoProgetto.PUBBLICO);
        selezionaProgetto();
    }

    public void selezionaProgetto() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
            System.out.println("Desideri candidarti a questo progetto?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                getCandidaturaController().creaCandidatura(idUtente, Long.valueOf(idProgetto));
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
