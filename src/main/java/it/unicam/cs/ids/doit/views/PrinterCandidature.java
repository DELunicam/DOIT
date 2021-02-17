package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Set;

public abstract class PrinterCandidature {
    private static CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }

    public static void printListaCandidature(Long idProgetto, StatoCandidatura statoCandidatura){
        Set<Candidatura> candidature = getCandidaturaController().getCandidatureByIdProgettoAndStato(idProgetto, statoCandidatura);
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