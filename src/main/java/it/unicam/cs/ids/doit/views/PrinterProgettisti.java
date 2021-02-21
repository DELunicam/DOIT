package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.gestori_utenti.GestoreProgettisti;
import it.unicam.cs.ids.doit.utenti.Progettista;

import java.util.Set;

public abstract class PrinterProgettisti {
    private static final GestoreProgettisti gestoreProgettisti = GestoreProgettisti.getInstance();

    public static void printListaProgettisti() {
        //TODO printListaProgetti
        Set<Progettista> progettisti = gestoreProgettisti.getListaProgettisti();
        System.out.println("ID, NOME, COGNOME");
        for (Progettista progettista : progettisti) {
            System.out.println(progettista.getUsername() + ", " + progettista.getNome() + ", " + progettista.getCognome());


        }
    
    }
    public static void printProgettistiAssociati(Set<Long> id)
    {

        Set<Progettista> progettisti = gestoreProgettisti.getListaProgettistiById(id);
        printInfoProgettisti(progettisti);
    }

    public static void printInfoProgettista(Long idProgettista) {

        Progettista progettista = gestoreProgettisti.getProgettista(gestoreProgettisti.getIdProgettistaByUsername(idProgettista));
        printInfoProgettista(progettista);

    }

    public static void printInfoProgettista(Progettista progettista) {
        System.out.println("ID: " + progettista.getUsername() +
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

    public static void printInfoProgettista(Candidatura candidatura){
        printInfoProgettista(candidatura.getIdProgettista());
    }

}
