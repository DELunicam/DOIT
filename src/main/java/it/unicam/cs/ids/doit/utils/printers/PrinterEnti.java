package it.unicam.cs.ids.doit.utils.printers;

import it.unicam.cs.ids.doit.gestori_utenti.EnteController;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Lavoratore;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterEnti {

    private static EnteController getEnteController() {
        return SpringContext.getBean(EnteController.class);
    }

    public static void printLavoratori(Long idEnte, Specializzazione specializzazione)
    {
        Set<Lavoratore> lavoratori = getEnteController().getLavoratori(idEnte, specializzazione);
        printInfoLavoratore(lavoratori);
    }
    public static void printInfoLavoratore(Set<Lavoratore> lavoratori)
    {
        lavoratori.forEach(PrinterEnti::printInfoLavoratore);
    }
    public static void printInfoLavoratore(Lavoratore lavoratore)
    {
         System.out.println("ID: " + lavoratore.getId() +
                "\nNome: " + lavoratore.getNome() +
                "\nCognome: " + lavoratore.getCognome() +
                "\nSpecializzazioni: " + lavoratore.getInfoSpec() +"\n");
               
    }
    public static void printInfoEnte(Long id)
    {
        getEnteController().getEnte(id);

    }
    public static void printInfoEnte(Ente ente)
   
    {
        System.out.println("ID: " +ente.getId()+
        "\n Nome: " + ente.getNome() +
        "\n Tipologia: " +ente.getTipologia() +
        "\n Descrizione: "+ente.getDescrizione());
    }


}