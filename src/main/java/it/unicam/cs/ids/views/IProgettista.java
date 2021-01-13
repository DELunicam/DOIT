package it.unicam.cs.ids.views;

import it.unicam.cs.ids.GestoriUtenti.GestoreProgettisti;
import it.unicam.cs.ids.candidatura.GestoreCandidature;
import it.unicam.cs.ids.progetto.*;
import it.unicam.cs.ids.utenti.Progettista;
import java.util.*;
import it.unicam.cs.ids.views.PrinterProgetti;

public class IProgettista
{
        Scanner sc;
        GestoreProgettisti gestoreP = new GestoreProgettisti();
        GestoreProgetto gestore = new GestoreProgetto();
        GestoreCandidature gestoreC = new GestoreCandidature();
        String idProgettista;
        

        public IProgettista(String idProgettista) {
            this.sc = new Scanner(System.in);
            this.idProgettista = idProgettista;
        } 

        public void candida()
        {
            System.out.println("Vuoi visualizzare i progetti a cui puoi candidarti? \n[Y] YES,    [N] NO)\n");
            String input = sc.nextLine().toUpperCase();
            if(input.equals("Y"))
            {  
                viewProgettiCandidabili();
                
            } else if(input.equals("N")){
                System.out.println("Ok, operazione annullata \n");
            } else{
                System.out.println("Impossibile processare l'operazione");
             }
        }   
        public void viewProgettiCandidabili()
        {
            System.out.println("Puoi candidarti ai seguenti progetti \n");
            Set <Progetto> progettiCandidabili = gestore.getListaProgetti(gestoreP.getSpecializzazioni(idProgettista));
            for(Progetto p : progettiCandidabili)
            {
                System.out.println(p.getId()+"\n");
            }
             selezionaProgetto();
        }
        public void selezionaProgetto() {
            System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
            String idProgetto = sc.nextLine();
            if(!idProgetto.equals("EXIT")) 
            {
                PrinterProgetti.printInfoProgetto(idProgetto);
                System.out.println("Desideri candidarti a questo progetto?\n[Y] YES,    [N] NO)\n");
                String input = sc.nextLine().toUpperCase();     
                if(input.equals("Y"))
                {  
                    gestoreC.creaCandidatura(idProgettista, idProgetto);
                    System.out.println("Congratulazioni, ti sei candidato al progetto " + idProgetto );
                
                } else if(input.equals("N")){
                System.out.println("Ok, operazione annullata \n");
                viewProgettiCandidabili();
                } else{
                System.out.println("Impossibile processare l'operazione");
                }
              
            }
        }
      
      

}
