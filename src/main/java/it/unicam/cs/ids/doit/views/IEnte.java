package it.unicam.cs.ids.doit.views;

import java.util.Scanner;
import java.util.Set;

import it.unicam.cs.ids.doit.associazione.GestoreAssociazioni;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.candidatura.GestoreCandidature;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.gestori_utenti.GestoreEnti;
import it.unicam.cs.ids.doit.gestori_utenti.GestoreProgettisti;
import it.unicam.cs.ids.doit.progetto.GestoreProgetto;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;

public class IEnte 
    {
     Scanner sc;
     Long id;
     GestoreEnti gestoreEnti = GestoreEnti.getInstance();
     GestoreProgetto gestoreProgetto = GestoreProgetto.getInstance();
     GestoreCandidature gestoreCandidature = GestoreCandidature.getInstance();
     GestoreAssociazioni gestoreAssociazioni = GestoreAssociazioni.getInstance();
     GestoreProgettisti gestoreProgettisti = GestoreProgettisti.getInstance();
     
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
        PrinterProgetti.printListaProgetti();
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
                gestoreCandidature.creaCandidatura(id, Long.valueOf(idProgetto));
                System.out.println("Congratulazioni, ti sei candidato al progetto " + idProgetto);

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                viewProgettiCandidabili();
            } else {
                System.out.println("Impossibile processare l'operazione");
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
        System.out.println("Puoi candidarti ai seguenti progetti \n");
        PrinterProgetti.printProgettiCandidati(gestoreCandidature.getIdProgetti(id, StatoCandidatura.ACCETTATA));
        selezionaProgettoAssociazione();
    }
     public void selezionaProgettoAssociazione()
     {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
            System.out.println("Desideri creare un'associazione per questo progetto?\n[Y] YES,    [N] NO)\n");
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
     public void selezionaProgettista(Long idProgetto)
     {  
        PrinterProgettisti.printListaProgettisti();
        System.out.println("Digitare l'id del progettista desiderato, [EXIT] per uscire");
        String idProgettista = sc.nextLine();
        if (!idProgettista.equals("EXIT")) {
            System.out.println("Desideri inviare una proposta di associazione a progettista"+idProgettista+"?\n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("Y")) {
                gestoreAssociazioni.creaAssociazione(id, Long.valueOf(idProgettista), idProgetto);
                System.out.println("Proposta di associazione inviata\n");
                //TODO notifica

            } else if (input.equals("N")) {
                System.out.println("Ok, operazione annullata \n");
                selezionaProgettista(idProgetto);
            } else {
                System.out.println("Impossibile processare l'operazione");
            }

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
        PrinterProgetti.printProgettiCandidati(gestoreCandidature.getIdProgetti(id, StatoCandidatura.PRESELEZIONATA));
        selezionaProgettoAssegnazione();
    }

    public void selezionaProgettoAssegnazione()
    {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
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
    }
    public void selezionaLavoratori(Long idProgetto)
    {
        System.out.println("I seguenti progettisti sono associati per questo progetto: \n");
        PrinterProgettisti.printInfoProgettisti(gestoreProgettisti.getListaProgettistiById(gestoreAssociazioni.getIdProgettisti(id, Long.valueOf(idProgetto))));
        System.out.println("Inserisci l'id dei lavoratori che vuoi assegnare a questo progetto\n");
        PrinterEnti.printInfoLavoratore(gestoreEnti.getLavoratori(id));
        int progettistiAttuali = gestoreProgettisti.getListaProgettistiById(gestoreAssociazioni.getIdProgettisti(id, Long.valueOf(idProgetto))).size();
        int progettistiRichiesti = gestoreProgetto.getProgetto(idProgetto).getNumeroProgettistiRichiesti();
        
        while(progettistiAttuali < progettistiRichiesti)
        {
            Long idLavoratore = Long.valueOf(sc.nextLine());
            gestoreEnti.assegnaProgetto(idLavoratore, idProgetto);
            progettistiAttuali++;
            System.out.println("Lavoratore " + idLavoratore + "assegnato al progetto " + idProgetto);
            //TODO notifica
        }

    }
}
