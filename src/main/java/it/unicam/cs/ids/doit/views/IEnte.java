package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.associazione.AssociazioneController;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.gestori_utenti.EnteController;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utils.SpringContext;

public class IEnte extends IUtente {

    private CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }

    public IEnte(Long idEnte) {
        super(idEnte);
    }

    private MessaggioController getMessaggioController()
    {
        return SpringContext.getBean(MessaggioController.class);
    }
    private AssociazioneController getAssociazioneController() {
        return SpringContext.getBean(AssociazioneController.class);
    }

    private ProgettoController getProgettoController() {
        return SpringContext.getBean(ProgettoController.class);
    }
  
    
   
    private ProgettistaController getProgettistaController() {
        return SpringContext.getBean(ProgettistaController.class);
    }

    private EnteController getEnteController() {
        return SpringContext.getBean(EnteController.class);
    }

    public void opzioniDisponibili() {
        while (true) {
            System.out.println("Cosa vuoi fare \n"
                    + "[INSERISCI CANDIDATURA] \n"
                    + "[ASSEGNA LAVORATORI] \n"
                    + "[INVIA PROPOSTA DI ASSOCIAZIONE]\n"
                    + "[INVIA MESSAGGIO]\n"
                    + "[VISUALIZZA NOTIFICHE]\n" +
                    "[LOGOUT]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "INSERISCI CANDIDATURA":
                    inserisciCandidatura();
                    break;
                case "INVIA PROPOSTA DI ASSOCIAZIONE":
                    PropostaAssociazione();
                    break;
                case "ASSEGNA LAVORATORI":
                    assegnaLavoratori();
                    break;
                case "INVIA MESSAGGIO":
                    inviaMessaggio();
                    break;
                case "VISUALIZZA NOTIFICHE":
                    visualizzaMessaggi();
                    break;
                case "LOGOUT":
                    logout();
                    break;
            }
        }
    }

    public void inserisciCandidatura() {
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
        PrinterProgetti.printListaProgetti(StatoProgetto.PUBBLICO);
        selezionaProgetto();
    }

    public void selezionaProgetto() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
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
        if (!idProgetto.equals("EXIT")) {
            if(progetto.getStatoProgetto()== StatoProgetto.PUBBLICO){
            PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
            System.out.println("Desideri candidarti a questo progetto?\n[Y] YES,    [N] NO\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                getCandidaturaController().creaCandidatura(id, Long.valueOf(idProgetto));
                System.out.println("Congratulazioni, ti sei candidato al progetto " + idProgetto);

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                viewProgettiCandidabili();
            } else {
                System.out.println("Impossibile processare l'operazione");
            }
        }
        else
        {
            System.out.println("Id progetto errato \n");
            selezionaProgetto();
        }
        }
    }}

    public void PropostaAssociazione() {
        System.out.println("Vuoi associare un progettista per un progetto? \n[Y] YES,    [N] NO)\n");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            viewProgettiCandidati();

        } else if (input.equals("N")) {
            System.out.println("Ok, operazione annullata \n");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    public void viewProgettiCandidati() {
        System.out.println("Sei candidato ai seguenti progetti \n");
        PrinterProgetti.printProgettiCandidati(getCandidaturaController().getIdProgetti(id, StatoCandidatura.ACCETTATA));
        selezionaProgettoAssociazione();
    }

    public void selezionaProgettoAssociazione() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
      
        if (!idProgetto.equals("EXIT")) {
            try{Long.valueOf(idProgetto);}
    
        catch (NumberFormatException e) {
            System.out.println("Inserisci un id valido");
            selezionaProgettoAssociazione();
            return;
        }
        Progetto progetto = getProgettoController().one(Long.valueOf(idProgetto));
        if (progetto == null) {
            System.out.println("Inserisci un id valido");
            selezionaProgettoAssociazione();
            return;
        }
        else{ 
          PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
            System.out.println("Desideri creare un'associazione per questo progetto?\n[Y] YES,    [N] NO\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                selezionaProgettista(Long.valueOf(idProgetto));

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                viewProgettiCandidati();
            } else {
                System.out.println("Impossibile processare l'operazione");
            }
            }
          
        }
    }
    

    public void selezionaProgettista(Long idProgetto) {
        PrinterProgettisti.printListaProgettisti();
        System.out.println("Digitare l'id del progettista desiderato, [EXIT] per uscire");
        String idProgettista = sc.nextLine();
        
        if (!idProgettista.equals("EXIT")) {
            try{Long.valueOf(idProgettista);}
    
        catch (NumberFormatException e) {
            System.out.println("Inserisci un id valido");
            selezionaProgettista(idProgetto);
            return;
        }
        Progettista progettista = getProgettistaController().one(id);
        if (progettista == null) {
            System.out.println("Inserisci un id valido");
            selezionaProgetto();
            return;
        }
        else{
            System.out.println("Desideri inviare una proposta di associazione a progettista" + idProgettista + "?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            
            if (input.equals("Y")) {
                getAssociazioneController().creaAssociazione(id, Long.valueOf(idProgettista), idProgetto);
                System.out.println("Proposta di associazione inviata\n");
                getMessaggioController().createMessaggio(id, progettista.getId(), "Hai ricevuto una proposta di associazione da:" +getEnteController().getEnte(id).getNome());

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                selezionaProgettista(idProgetto);
            } else {
                System.out.println("Impossibile processare l'operazione");
            }
        }
     }

    }

    public void assegnaLavoratori() {
        System.out.println("Vuoi assegnare dei lavoratori ad un progetto? \n[Y] YES,    [N] NO)\n");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            viewProgettiAssegnati();

        } else if (input.equals("N")) {
            System.out.println("Ok, operazione annullata \n");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    public void viewProgettiAssegnati() {
        System.out.println("I progetti a te assegnati sono: \n");
        PrinterProgetti.printProgettiCandidati(getCandidaturaController().getIdProgetti(id, StatoCandidatura.PRESELEZIONATA));
        selezionaProgettoAssegnazione();
    }

    public void selezionaProgettoAssegnazione() {
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
            PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
            System.out.println("Desideri assegnare dei lavoratori a questo progetto?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                selezionaLavoratori(Long.valueOf(idProgetto));

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                viewProgettiCandidati();
            } else {
                System.out.println("Impossibile processare l'operazione");
            }

        }
    }}

    public void selezionaLavoratori(Long idProgetto) {
        System.out.println("I seguenti progettisti sono associati per questo progetto: \n");
        PrinterProgettisti.printInfoProgettisti(getProgettistaController().getProgettistiCandidati(getAssociazioneController().getIdProgettisti(id, Long.valueOf(idProgetto))));
        System.out.println("Inserisci l'id dei lavoratori che vuoi assegnare a questo progetto\n");
        PrinterEnti.printInfoLavoratore(getEnteController().getLavoratori(id));
        int progettistiAttuali = getProgettistaController().getProgettistiCandidati(getAssociazioneController().getIdProgettisti(id, Long.valueOf(idProgetto))).size();
        int progettistiRichiesti = getProgettoController().one(idProgetto).getNumeroProgettistiRichiesti();

        while (progettistiAttuali < progettistiRichiesti) {
            Long idLavoratore = Long.valueOf(sc.nextLine());
            try
            {
                Long.valueOf(idLavoratore);
            }
            
            catch(NumberFormatException e)
            {
                System.out.println("Inserisci un id valido");
                selezionaLavoratori(idProgetto);
                return;
            }

            getEnteController().assegnaProgetto(idLavoratore, idProgetto);
            progettistiAttuali++;
            System.out.println("Lavoratore " + idLavoratore + "assegnato al progetto " + idProgetto);
        
            }

        
    }
}

