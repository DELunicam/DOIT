package it.unicam.cs.ids.views;

import it.unicam.cs.ids.progetto.GestoreProgetto;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utenti.Progettista;

import java.util.Set;

public abstract class PrinterProgetti {
    private static final GestoreProgetto gestoreProgetto = GestoreProgetto.getInstance();

    public static void printListaProgetti() {
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti();
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(String idProponente) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti(idProponente);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(StatoProgetto statoProgetto) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti(statoProgetto);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(String idProponente, StatoProgetto statoProgetto) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti(idProponente, statoProgetto);
        printBasicProgetti(progetti);
    }

    public static void printListaProgetti(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti(specializzazioni, statoProgetto);
        printBasicProgetti(progetti);
    }

    public static void printListaProgettiSvolti(String idProgettista) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgettiSvolti(idProgettista);
        printBasicProgetti(progetti);
    }

    public static void printListaProgettiSvolti(Progettista progettista) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgettiSvolti(progettista);
        printBasicProgetti(progetti);
    }

    private static void printBasicProgetti(Set<Progetto> progetti) {
        if (progetti.size() == 0) {
            System.out.println("Non sono stati trovati progetti");
        } else {
            System.out.println("ID, NOME, STATO");
            for (Progetto progetto : progetti) {
                System.out.println(progetto.getId() + ", " + progetto.getNome() + ", " + progetto.getStatoProgetto());
            }
        }
    }

    public static void printInfoProgetto(String idProgetto) {
        Progetto progetto = gestoreProgetto.getProgetto(idProgetto);
        printInfoProgetto(progetto);
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

    public static void printInfoProgetto(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto) {
        Set<Progetto> progetti = gestoreProgetto.getListaProgetti(specializzazioni, statoProgetto);
        progetti.forEach(PrinterProgetti::printInfoProgetto);
    }
}