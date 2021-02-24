package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.utils.printers.PrinterCandidature;
import it.unicam.cs.ids.doit.utils.printers.PrinterProgetti;
import it.unicam.cs.ids.doit.utils.printers.PrinterProgettisti;
import it.unicam.cs.ids.doit.valutazione.ValutazioneController;

import java.util.*;

public class IEsperto extends IUtente {

    private CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }

    private ValutazioneController getValutazioneController() {
        return SpringContext.getBean(ValutazioneController.class);
    }

    private ProgettoController getProgettoController() {
        return SpringContext.getBean(ProgettoController.class);
    }


    public IEsperto(Long idEsperto) {
        super(idEsperto);
    }

    public void opzioniDisponibili() {
        while (true) {
            System.out.println("Cosa vuoi fare\n" +
                    "[VALUTA PROPOSTA]\n" +
                    "[VALUTA PROGETTISTI]\n" +
                    "[INVIA MESSAGGIO]\n" +
                    "[VISUALIZZA NOTIFICHE]\n" +
                    "[VISUALIZZA PROGETTI]\n" +
                    "[VISUALIZZA PROGETTISTI]\n" +
                    "[LOGOUT]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "VALUTA PROPOSTA":
                    valutaProposta();
                    break;
                case "VALUTA PROGETTISTI":
                    valutaProgettisti();
                    break;
                case "INVIA MESSAGGIO":
                    inviaMessaggio();
                    break;
                case "VISUALIZZA NOTIFICHE":
                    visualizzaMessaggi();
                    break;
                case "VISUALIZZA PROGETTI":
                    visualizzaListaProgetti();
                    break;
                case "VISUALIZZA PROGETTISTI":
                    visualizzaProgettisti();
                    break;
                case "LOGOUT":
                    logout();
                    break;
            }
        }
    }

    // CASO D'USO
    public void valutaProposta() {
        Set<Progetto> progetti = getProgettoController().allByStato(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
        if (progetti.size() == 0) {
            System.out.println("Non ci sono progetti da valutare");
            return;
        }
        PrinterProgetti.printBasicProgetti(progetti);
        System.out.println("Digitare l'id del progetto per selezionarlo e visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        while (!idProgetto.equals("EXIT")) {
            try {
                Long idProgettoL = Long.valueOf(idProgetto);
                PrinterProgetti.printInfoProgetto(idProgettoL);
                System.out.println("Si vuole valutare questa proposta di progetto? \n" +
                        "[Y] YES,    [N] NO");
                String conferma = sc.nextLine().toUpperCase();

                if (conferma.equals("Y")) {
                    System.out.println("Si ritiene realizzabile questa proposta di progetto? \n" +
                            "[Y] YES,    [N] NO");
                    String fattibile = sc.nextLine().toUpperCase();
                    if (fattibile.equals("Y")) {
                        getValutazioneController().creaValutazionePositiva(idProgettoL, id, this.requestProgettistiECompetenze());
                        System.out.println("Valutazione completa inviata \n");
                    } else {
                        getValutazioneController().creaValutazioneNegativa(idProgettoL, id);
                        System.out.println("Valutazione negativa inviata \n");
                    }
                } else {
                    System.out.println("Digitare l'id di un altro progetto, [EXIT] per uscire");
                    idProgetto = sc.nextLine();
                }
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Inserire un id valido");
                idProgetto = sc.nextLine();
            }
        }
    }

    // CASO D'USO
    public void valutaProgettisti() {
        Set<Long> idsConsigliate = new HashSet<>();
        Set<Long> idsSconsigliate = new HashSet<>();

        Set<Progetto> progetti = getProgettoController().allByStato(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
        if (progetti.size() == 0) {
            System.out.println("Non ci sono progetti per cui è possibile valutare candidature");
            return;
        }
        PrinterProgetti.printBasicProgetti(progetti);
        System.out.println("Digitare l'id del progetto per selezionarlo e visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            try {
                Long idProgettoL = Long.valueOf(idProgetto);
                System.out.println("Dettagli progetto " + idProgettoL + ":");
                PrinterProgetti.printInfoProgetto(idProgettoL);
                System.out.println("Candidature al progetto " + idProgettoL + ":");
                PrinterCandidature.printListaCandidature(idProgettoL, StatoCandidatura.DA_VALUTARE);
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Inserire un id valido");
                valutaProgettisti();
                return;
            }
            System.out.println("Digitare l'id del progettista di cui si vogliono visualizzare i dettagli, [DONE] per uscire");
            while (true) {
                String idInput = sc.nextLine();
                if (idInput.equals("DONE")) {
                    break;
                }
                try {
                    PrinterProgettisti.printInfoProgettista(Long.valueOf(idInput));
                } catch (NumberFormatException | NoSuchElementException e) {
                    System.out.println("nserire un id valido");
                    continue;
                }
                System.out.println("Si vuole consigliare questo progettista per lavorare al progetto? \n" +
                        "[Y] YES,    [N] NO");
                String conferma = sc.nextLine().toUpperCase();
                if (conferma.equals("Y")) {
                    Candidatura candidatura = getCandidaturaController().getCandidaturaByIdProgettoAndIdProgettista(Long.valueOf(idProgetto), Long.valueOf(idInput));
                    getCandidaturaController().aggiungiCandidatura(candidatura.getId(), idsConsigliate);
                } else if (conferma.equals("N")) {
                    Candidatura candidatura = getCandidaturaController().getCandidaturaByIdProgettoAndIdProgettista(Long.valueOf(idProgetto), Long.valueOf(idInput));
                    getCandidaturaController().aggiungiCandidatura(candidatura.getId(), idsSconsigliate);
                } else {
                    System.out.println("Impossibile eseguire l'operazione");
                }
                System.out.println("Inserisci l'id del prossimo progettista");
            }

            System.out.println("Si vogliono confermare le valutazioni sui progettisti candidati? \n" +
                    "[Y] YES,    [N] NO");
            String scelta = sc.nextLine().toUpperCase();
            if (scelta.equals("Y")) {
                getCandidaturaController().aggiungiPareriEsperto(id, idsConsigliate, idsSconsigliate);
                System.out.println("Valutazioni inviate");
            } else if (scelta.equals("N")) {
                System.out.println("Valutazioni non inviate");
            } else {
                System.out.println("Impossibile eseguire l'operazione");
            }
        }
    }


    public Map<Specializzazione, Integer> requestProgettistiECompetenze() {
        System.out.println("Inserire i progettisti richiesti e le loro specializzazioni separati da una virgola(,)\n" +
                "Esempio: CHIMICA, 2\n" +
                "Scrivere [DONE] per confermare\n" +
                "Specializzazioni attualmente disponibili: ");
        for (Specializzazione s : Specializzazione.values()) {
            System.out.print(s.toString() + ", ");
        }
        System.out.println();

        Map<Specializzazione, Integer> infoProgettistiRichiesti = new HashMap<>();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("DONE")) {
                break;
            }
            try {
                String[] inputs = input.split(",");
                String specializzazione = inputs[0];
                String numProgettisti = inputs[1].trim();
                if (checkSpecializzazione(specializzazione) && checkNumero(numProgettisti)) {
                    infoProgettistiRichiesti.put(Specializzazione.valueOf(specializzazione), Integer.parseInt(numProgettisti));
                    System.out.println("Specializzazione [" + specializzazione + "] e relativo numero progettisti richiesti aggiunti");
                } else {
                    System.out.println("Inserire una specializzazione valida");
                }
            } catch (Exception e) {
                System.out.println("Inserire una specializzazione valida");
            }
        }


        return infoProgettistiRichiesti;
    }

    private boolean checkSpecializzazione(String specInserita) {
        for (Specializzazione specDisponibile : Specializzazione.values()) {
            if (specInserita.equals(specDisponibile.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNumero(String numeroInserito) {
        try {
            Integer.parseInt(numeroInserito);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
