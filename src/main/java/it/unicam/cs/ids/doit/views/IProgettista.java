package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.associazione.Associazione;
import it.unicam.cs.ids.doit.associazione.AssociazioneController;
import it.unicam.cs.ids.doit.associazione.StatoAssociazione;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.utils.printers.PrinterAssociazioni;
import it.unicam.cs.ids.doit.utils.printers.PrinterProgetti;

public class IProgettista extends IUtente {

    private ProgettoController getProgettoController()
    {
        return SpringContext.getBean(ProgettoController.class);
    }
    private ProgettistaController getProgettistaController() {
        return SpringContext.getBean(ProgettistaController.class);
    }
    private CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }
    private AssociazioneController getAssociazioneController()
    {
        return SpringContext.getBean(AssociazioneController.class);
    }


    public IProgettista(Long idProgettista) {
        super(idProgettista);
    }

    public void opzioniDisponibili(){
        while (true) {
            System.out.println("Cosa vuoi fare?\n" +
                    "[CANDIDA]\n" +
                    "[ACCETTA ASSOCIAZIONE]\n" +
                    "[INVIA MESSAGGIO]\n" +
                    "[VISUALIZZA NOTIFICHE]\n" +
                    "[VISUALIZZA PROGETTI]\n" +
                    "[VISUALIZZA PROGETTISTI]\n" +
                    "[LOGOUT]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "CANDIDA":
                    candida();
                    break;
                case "ACCETTA ASSOCIAZIONE":
                    accettaAssociazione();
                    break;
                case "INVIA MESSAGGIO":
                    inviaMessaggio();
                    break;
                case "VISUALIZZA NOTIFICHE":
                    visualizzaMessaggi();
                    break;
                case "VISUALIZZA PROGETTI":
                    visualizzaListaProgetti();
                    break;
                case "VISUALIZZA PROGETTISTI":
                    visualizzaProgettisti();
                    break;
                case "LOGOUT":
                    logout();
                    break;

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
        PrinterProgetti.printListaProgetti(getProgettistaController().getSpecializzazioniByIdProgettista(id), StatoProgetto.PUBBLICO);
        selezionaProgetto();
    }

    public void selezionaProgetto() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
      
        if (!idProgetto.equals("EXIT")) {
            try{Long.valueOf(idProgetto);}
    
            catch (NumberFormatException e) {
                System.out.println("Inserisci un id valido");
                selezionaProgetto();
                return;
            }
            Progetto progetto = getProgettoController().one(Long.valueOf(idProgetto));
            if (progetto == null) {
                System.out.println("Inserisci un id valido");
                selezionaProgetto();
                return;
            }
            else{
            
            PrinterProgetti.printInfoProgetto(progetto.getId());
            System.out.println("Desideri candidarti a questo progetto?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                getCandidaturaController().creaCandidatura(id, progetto.getId());
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
    public void accettaAssociazione()
    {
        System.out.println("Vuoi visualizzare le richieste di associazioni? \n[Y] YES,    [N] NO)\n");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {

            viewRichiesteAssociazione();

        } else if (input.equals("N")) {
            System.out.println("Ok, operazione annullata \n");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }

    }

    public void viewRichiesteAssociazione()
    {
        PrinterAssociazioni.printListaAssociazione(id, StatoAssociazione.PROPOSTA);
        selezionaAssociazione();
    }

    public void selezionaAssociazione()
    {
        System.out.println("Digitare l'id dell'associazione desiderata, [EXIT] per uscire");
        String idAssociazione = sc.nextLine();
      
        if (!idAssociazione.equals("EXIT")) {
            try
            {
                Long.valueOf(idAssociazione);
            }
            catch (NumberFormatException e) {
                System.out.println("Inserisci un id valido");
                selezionaAssociazione();
                return;
            }
            Associazione associazione = getAssociazioneController().getAssociazioneById(Long.valueOf(idAssociazione));
            if (associazione == null) {
                System.out.println("Inserisci un id valido");
                selezionaAssociazione();
                return;
            }
            else{

            PrinterAssociazioni.printInfoAssociazione(Long.valueOf(idAssociazione));
            System.out.println("Desideri accettare questa richiesta?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                getAssociazioneController().modificaStatoAssociazione( getAssociazioneController().getAssociazioneById(Long.valueOf(idAssociazione)), StatoAssociazione.ACCETTATA_PROGETTISTA);
                System.out.println("Congratulazioni, Richiesta accettata ");

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                viewRichiesteAssociazione();
            } else {
                System.out.println("Impossibile processare l'operazione");
            }


        }
    }

    }
}
