package it.unicam.cs.ids.doit.views;

<<<<<<< Updated upstream
import java.util.Scanner;
import java.util.Set;

=======
import ch.qos.logback.core.joran.conditional.ElseAction;
>>>>>>> Stashed changes
import it.unicam.cs.ids.doit.associazione.AssociazioneController;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.gestori_utenti.EnteController;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utils.SpringContext;

public class IEnte extends IUtente
    {
     Scanner sc;
     Long id;

    private CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }
<<<<<<< Updated upstream
    private ProgettistaController getProgettistaController(){
       return SpringContext.getBean(ProgettistaController.class);
=======
    private MessaggioController getMessaggioConroller()
    {
        return SpringContext.getBean(MessaggioController.class);
    }

    public IEnte(Long idEnte) {
        super(idEnte);
>>>>>>> Stashed changes
    }
    private AssociazioneController getAssociazioneController() {
        return SpringContext.getBean(AssociazioneController.class);
    }
    private ProgettoController getProgettoController() {
        return SpringContext.getBean(ProgettoController.class);
    }
    private EnteController getEnteController()
      {
          return SpringContext.getBean(EnteController.class);
      }
     
    public IEnte(Long id)
    {
        this.sc = new Scanner(System.in);
        this.id= id;
    }
    
    public void opzioniDisponibili()
    {
        while(true)
        {
            System.out.println("Cosa vuoi fare \n"
                +"[INSERISCI CANDIDATURA] \n"
                +"[ASSEGNA LAVORATORI] \n"
                +"[INVIA PROPOSTA DI ASSOCIAZIONE]\n");
                String input = sc.nextLine().toUpperCase();
                switch (input) 
                {
                    case "INSERISCI CANDIDATURA":
                        inserisciCandidatura();
                        break;
                    case "INVIA PROPOSTA DI ASSOCIAZIONE":
                        PropostaAssociazione();
                        break;
                    case "ASSEGNA LAVORATORI":
                        assegnaLavoratori();
                        break;
                    case "EXIT":
                        return;
                }
        }
   }

   public void inserisciCandidatura()
   {
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
        if (!idProgetto.equals("EXIT")) {
            if(getProgettoController().checkIdProgetto(Long.valueOf(idProgetto)) == true && getProgettoController().checkStatoProgetto(Long.valueOf(idProgetto),StatoProgetto.PUBBLICO))
            {
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
                System.out.println("ID progetto errato");
                selezionaProgetto();
            }
        }
    }

    public void PropostaAssociazione()
    {
        System.out.println("Vuoi associare un progettista per un progetto? \n[Y] YES,    [N] NO)\n");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) 
         {
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

     public void selezionaProgettoAssociazione()
     {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            if(getProgettoController().checkIdProgetto(Long.valueOf(idProgetto)) == true && getCandidaturaController().checkStatoCandidatura(id, Long.valueOf(idProgetto), StatoCandidatura.ACCETTATA))
            {
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
            else {
                System.out.println("ID progetto errato");
                selezionaProgettoAssociazione();
            }

        }
        
     }

     public void selezionaProgettista(Long idProgetto)
     {  
        PrinterProgettisti.printListaProgettisti();
        System.out.println("Digitare l'id del progettista desiderato, [EXIT] per uscire");
        String idProgettista = sc.nextLine();
        if (!idProgettista.equals("EXIT")) {
<<<<<<< Updated upstream
            System.out.println("Desideri inviare una proposta di associazione a progettista"+idProgettista+"?\n[Y] YES,    [N] NO)\n");
=======
           // if(getProgettistaController().checkProgettisti(Long.valueOf(idProgettista)) == true ){
            System.out.println("Desideri inviare una proposta di associazione a progettista" + idProgettista + "?\n[Y] YES,    [N] NO)\n");
>>>>>>> Stashed changes
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                getAssociazioneController().creaAssociazione(id, Long.valueOf(idProgettista), idProgetto);
                System.out.println("Proposta di associazione inviata\n");
                getMessaggioConroller().createMessaggio(id, Long.valueOf(idProgettista), "L'ente: "+id +"ti ha inviato una richiesta di associazione\n");

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                selezionaProgettista(idProgetto);
            } else {
                System.out.println("Impossibile processare l'operazione");
            }
       /* }

        else
        {
            System.out.println("Id progettista errato \n");
            selezionaProgettista(idProgetto);
        }
        */
        }
 
    }
    public void assegnaLavoratori()
    {
        System.out.println("Vuoi assegnare dei lavoratori ad un progetto? \n[Y] YES,    [N] NO)\n");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) 
         {
            viewProgettiAssegnati();
    
        } else if (input.equals("N")) {
            System.out.println("Ok, operazione annullata \n");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    public void viewProgettiAssegnati()
    {
        System.out.println("I progetti a te assegnati sono: \n");
        PrinterProgetti.printProgettiCandidati(getCandidaturaController().getIdProgetti(id, StatoCandidatura.PRESELEZIONATA));
        selezionaProgettoAssegnazione();
    }

    public void selezionaProgettoAssegnazione()
    {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            if(getProgettoController().checkIdProgetto(Long.valueOf(idProgetto)) == true && getCandidaturaController().checkStatoCandidatura(id, Long.valueOf(idProgetto), StatoCandidatura.PRESELEZIONATA))
            {
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
            else
            {
                System.out.println("Id progetto errato \n");
                selezionaProgettoAssociazione();

            }
        }
    }
    public void selezionaLavoratori(Long idProgetto)
    {
        System.out.println("I seguenti progettisti sono associati per questo progetto: \n");
        PrinterProgettisti.printInfoProgettisti(getProgettistaController().getProgettistiCandidati(getAssociazioneController().getIdProgettisti(id, Long.valueOf(idProgetto))));
        System.out.println("Inserisci l'id dei lavoratori che vuoi assegnare a questo progetto\n");
        PrinterEnti.printInfoLavoratore(getEnteController().getLavoratori(id));
        int progettistiAttuali = getProgettistaController().getProgettistiCandidati(getAssociazioneController().getIdProgettisti(id, Long.valueOf(idProgetto))).size();
        int progettistiRichiesti = getProgettoController().one(idProgetto).getNumeroProgettistiRichiesti();
        
        while(progettistiAttuali < progettistiRichiesti)
        {
            Long idLavoratore = Long.valueOf(sc.nextLine());
            if(getEnteController().checkLavoratori(id, Long.valueOf(idLavoratore)) == true)
            {
            getEnteController().assegnaProgetto(idLavoratore, idProgetto);
            progettistiAttuali++;
            System.out.println("Lavoratore " + idLavoratore + "assegnato al progetto " + idProgetto);
        
            }
        else
        {
            System.out.println("Id Lavoratore errato \n");
            selezionaLavoratori(idProgetto);
        }

    }
    }
}
