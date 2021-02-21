package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaController;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterProgettisti {
    private static ProgettistaController getProgettistaController() {
        return SpringContext.getBean(ProgettistaController.class);
    }

    public static void printListaProgettisti() {
        //TODO printListaProgetti
        Set<Progettista> progettisti = getProgettistaController().all();
        System.out.println("ID, USERNAME, NOME, COGNOME");
        for (Progettista progettista : progettisti) {
            System.out.println(progettista.getId() + " " +progettista.getUsername() + ", " + progettista.getNome() + ", " + progettista.getCognome());
        }
    }

    public static void printBasicProgettisti(Set<Progettista> progettisti) {
        if (progettisti.size() == 0) {
            System.out.println("Non sono stati trovati progetti");
        } else {
            System.out.println("ID, NOME, COGNOME, SPECIALIZZAZIONI");
            for (Progettista progettista : progettisti) {
                System.out.println(progettista.getId() + ", " + progettista.getNome() + ", " + progettista.getCognome()
                + ", " + progettista.getSpecializzazioni());
            }
        }
    
    }
    public static void printProgettistiAssociati(Set<Long> id)
    {

        Set<Progettista> progettisti = gestoreProgettisti.getListaProgettistiById(id);
        printInfoProgettisti(progettisti);
    }

    public static void printInfoProgettista(Long idProgettista) {
        Progettista progettista = getProgettistaController().one(idProgettista);
        printInfoProgettista(progettista);
    }

    public static void printInfoProgettista(Progettista progettista) {
        System.out.println("ID: " + progettista.getUsername() +
                "\nEmail: " + progettista.getMailAddress() +
                "\nNome: " + progettista.getNome() +
                "\nCognome: " + progettista.getCognome() +
                "\nSpecializzazioni: " + progettista.getSpecializzazioni());
    }

    public static void printInfoProgettisti(Set<Progettista> progettisti){
        progettisti.forEach(PrinterProgettisti::printInfoProgettista);
    }

    public static void printInfoProgettista(Candidatura candidatura){
        printInfoProgettista(candidatura.getIdProgettista());
    }

}
