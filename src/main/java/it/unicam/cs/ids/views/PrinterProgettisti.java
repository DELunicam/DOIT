package it.unicam.cs.ids.views;

import it.unicam.cs.ids.GestoriUtenti.GestoreProgettisti;
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
        System.out.println("ID: " + progettista.getId() +
                "\nEmail: " + progettista.getMailAddress() +
                "\nNome: " + progettista.getNome() +
                "\nCognome: " + progettista.getCognome() +
                "\nSpecializzazioni: " + progettista.getInfoSpec() +
                "\nProgetti Svolti: " + gestoreProgettisti.getProgettiSvolti(idProgettista)
        );

    }
}
