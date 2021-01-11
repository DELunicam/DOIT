package it.unicam.cs.ids.views;

import it.unicam.cs.ids.progetto.GestoreProgetto;
import it.unicam.cs.ids.progetto.Progetto;

import java.util.Set;

public abstract class PrinterProgetti {
    private static final GestoreProgetto gestoreProgetto = GestoreProgetto.getInstance();

    public static void printListaProgetti(){
        //TODO printListaProgetti
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti();
        System.out.println("ID, NOME, STATO");
        for(Progetto progetto : progetti){
            System.out.println(progetto.getId()+", "+progetto.getNome()+", "+progetto.getStatoProgetto());
        }
    }

    public static void printInfoProgetto(String idProgetto){
        //TODO printInfoProgetto
        Progetto progetto = gestoreProgetto.getProgetto(idProgetto);
        System.out.println("IdProgetto: "+progetto.getId());
        System.out.println("Nome: "+progetto.getNome());
        System.out.println("Descrizione: "+progetto.getDescrizione());
        System.out.println("Stato: "+progetto.getStatoProgetto());
        System.out.println("IdProponente: "+progetto.getStatoProgetto());
        System.out.println("Progettisti richiesti: \n" +
                "SPECIALIZZAZONE | NUMERO");
        progetto.getInfoProgettistiRichiesti().forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
