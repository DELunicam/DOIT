package it.unicam.cs.ids.views;

import it.unicam.cs.ids.GestoriUtenti.GestoreProgettisti;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.utenti.Progettista;

import java.util.Set;

public abstract class PrinterProgettisti {
    private static final GestoreProgettisti gestoreProgettisti = GestoreProgettisti.getInstance();

    public static void printListaProgettisti() {
        //TODO printListaProgetti
        Set<Progettista> progettisti = gestoreProgettisti.getListaProgettisti();
        System.out.println("ID, NOME, COGNOME");
        for (Progettista progettista : progettisti) {
            System.out.println(progettista.getId() + ", " + progettista.getNome() + ", " + progettista.getCognome());


        }
    }

    public static void printInfoProgettista(String idProgettista) {
        Progettista progettista = gestoreProgettisti.getProgettista(idProgettista);
        printInfoProgettista(progettista);

    }

    public static void printInfoProgettista(Progettista progettista){
        System.out.println("ID: " + progettista.getId() +
                "\nEmail: " + progettista.getMailAddress() +
                "\nNome: " + progettista.getNome() +
                "\nCognome: " + progettista.getCognome() +
                "\nSpecializzazioni: " + progettista.getInfoSpec() +
                "\nProgetti Svolti: \n");
        PrinterProgetti.printListaProgettiSvolti(progettista);
    }

    public static void printInfoProgettisti(Set<Progettista> progettisti){
        progettisti.forEach(PrinterProgettisti::printInfoProgettista);
    }
}
