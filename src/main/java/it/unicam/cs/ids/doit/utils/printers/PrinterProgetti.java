<<<<<<< HEAD:src/main/java/it/unicam/cs/ids/doit/utils/printers/PrinterProgetti.java
package it.unicam.cs.ids.doit.utils.printers;
=======
package it.unicam.cs.ids.doit.views.printers;
>>>>>>> enzo:src/main/java/it/unicam/cs/ids/doit/views/printers/PrinterProgetti.java

import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterProgetti {
    private static ProgettoController getProgettoController() {
        return SpringContext.getBean(ProgettoController.class);
    }

    public static void printListaProgetti() {
        Set<Progetto> progetti = getProgettoController().all();
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(Long idProponente) {
        Set<Progetto> progetti = getProgettoController().allByIdProponente(idProponente);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(StatoProgetto statoProgetto) {
        Set<Progetto> progetti = getProgettoController().allByStato(statoProgetto);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(Long idProponente, StatoProgetto statoProgetto) {
        Set<Progetto> progetti = getProgettoController().allByIdProponenteAndStato(idProponente, statoProgetto);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto) {
        Set<Progetto> progetti = getProgettoController().allByStatoProgettoAndSpecializzazione(specializzazioni, statoProgetto);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(Set<Progetto> progetti) {
        printBasicProgetti(progetti);
    }

    public static void printListaProgettiSvolti(Long idProgettista) {
        Set<Progetto> progetti = getProgettoController().getListaProgettiSvolti(idProgettista);
        System.out.println("Progetti svolti: ");
        printBasicProgetti(progetti);
    }
    

    public static void printListaProgettiSvolti(Progettista progettista) {
        Set<Progetto> progetti = getProgettoController().getListaProgettiSvolti(progettista);
        System.out.println("Progetti svolti: ");
        printBasicProgetti(progetti);
    }

    public static void printProgettiCandidati(Set<Long> ids) {
        Set<Progetto> progetti = getProgettoController().getProgettiCandidati(ids);
        printBasicProgetti(progetti);
    }

    public static void printBasicProgetti(Set<Progetto> progetti) {
        if (progetti.size() == 0) {
            System.out.println("Non sono stati trovati progetti");
        } else {
            System.out.println("ID, NOME, STATO");
            for (Progetto progetto : progetti) {
                System.out.println(progetto.getId() + ", " + progetto.getNome() + ", " + progetto.getStatoProgetto());
            }
        }
    }

    public static void printInfoProgetto(Long idProgetto) {
        Progetto progetto = getProgettoController().one(idProgetto);
        if (progetto != null) {
            printInfoProgetto(progetto);
        }
        else {
            System.out.println("Impossibile trovare il progetto desiderato");
        }
    }
    

    public static void printInfoProgetto(Progetto progetto) {
        System.out.println("----------------------------");
        System.out.println("IdProgetto: " + progetto.getId());
        System.out.println("Nome: " + progetto.getNome());
        System.out.println("Descrizione: " + progetto.getDescrizione());
        System.out.println("Stato: " + progetto.getStatoProgetto());
        System.out.println("IdProponente: " + progetto.getIdProponente());
        System.out.println("Progettisti richiesti: \n" +
                "SPECIALIZZAZONE | NUMERO");
        progetto.getInfoProgettistiRichiesti().forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println("----------------------------");
    }

}