package it.unicam.cs.ids.views;

import it.unicam.cs.ids.candidatura.Candidatura;
import it.unicam.cs.ids.candidatura.GestoreCandidature;
import it.unicam.cs.ids.candidatura.StatoCandidatura;

import java.util.Set;

public abstract class PrinterCandidature {
    private static final GestoreCandidature gestoreCandidature = GestoreCandidature.getInstance();

    public static void printListaCandidature(String idProgetto, StatoCandidatura statoCandidatura){
        Set<Candidatura> candidature = gestoreCandidature.getCandidature(idProgetto, statoCandidatura);
        printBasicCandidature(candidature);
    }

    private static void printBasicCandidature(Set<Candidatura> candidature) {
        System.out.println("ID_candidatura, Stato_candidatura, ID_progettista, ID_progetto");
        for (Candidatura candidatura : candidature) {
            System.out.println(candidatura.getId() + ", "
                    + candidatura.getStatoCandidatura() + ", "
                    + candidatura.getIdProgettista() + ", "
                    + candidatura.getIdProgetto());

        }
    }
}