package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.associazione.AssociazioneController;
import it.unicam.cs.ids.doit.associazione.StatoAssociazione;
import it.unicam.cs.ids.doit.associazione.Associazione;


import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterAssociazioni {
    private static AssociazioneController getAssociazioneController() {
        return SpringContext.getBean(AssociazioneController.class);
    }

    public static void printListaAssociazione(Long idProgettista, StatoAssociazione statoAssociazione){
        Set<Associazione> associazioni = getAssociazioneController().getAssociazioniByIdProgettistaAndStato(idProgettista, statoAssociazione);
        printBasicAssociazione(associazioni);
    }

    private static void printBasicAssociazione(Set<Associazione> associazioni) {
        System.out.println("ID_associazione, Id_Ente, ID_progettista, ID_progetto");
        for (Associazione associazione : associazioni) 
        {
            System.out.println(associazione.getId() + ", "
                    + associazione.getIdEnte() + ", "
                    + associazione.getIdProgettista() + ", "
                    + associazione.getIdProgetto());
                    
            
        }
    }
    public static void printInfoAssociazione(Long idAssociazione)
    {
        Associazione associazione = getAssociazioneController().getAssociazioneById(idAssociazione);
        printInfoAssociazione(associazione);
    }
    public static void printInfoAssociazione(Associazione associazione) {
        System.out.println("----------------------------");
        System.out.println("IdAssociazione: " + associazione.getId());
        PrinterProgetti.printInfoProgetto(associazione.getIdProgetto());
        PrinterEnti.printInfoEnte(associazione.getIdEnte());     
        System.out.println("----------------------------");
    }
}