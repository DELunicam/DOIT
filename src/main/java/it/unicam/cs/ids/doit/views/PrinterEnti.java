package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.gestori_utenti.EnteController;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.gestori_utenti.GestoreEnti;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Lavoratore;

import java.util.HashSet;
import java.util.Set;

public abstract class PrinterEnti {

    private static final GestoreEnti gestoreEnti = GestoreEnti.getInstance();

    public static void printLavoratori(Long idEnte, Specializzazione specializzazione)
    {
        Set<Lavoratore> lavoratori = gestoreEnti.getLavoratori(idEnte, specializzazione);
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

}