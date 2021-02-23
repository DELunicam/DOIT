package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidaturaController;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoController;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class IProponente extends IUtente {

    private static ProgettoController getProgettoController() {
        return SpringContext.getBean(ProgettoController.class);
    }

    private CandidaturaController getCandidaturaController() {
        return SpringContext.getBean(CandidaturaController.class);
    }

    public IProponente(Long idProponente) {
        super(idProponente);
    }

    public void opzioniDisponibili() {
        while (true) {
            System.out.println("Cosa vuoi fare?\n"
                    + "[CREA]\n"
                    + "[PUBBLICA]\n"
                    + "[SELEZIONA PROGETTISTI]\n"
                    + "[CONFERMA PROGETTISTI]\n"
                    + "[INVIA MESSAGGIO]\n"
                    + "[VISUALIZZA NOTIFICHE]\n"
                    + "[LOGOUT]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "CREA":
                    startProgetto();
                    break;

                case "PUBBLICA":
                    Set<Progetto> progettiNeutri = getProgettoController().allByIdProponenteAndStato(id,
                            StatoProgetto.NEUTRO);
                    if (progettiNeutri.size() > 0) {
                        PrinterProgetti.printListaProgetti(progettiNeutri);
                        selezionaProgetto(StatoProgetto.NEUTRO);
                    } else {
                        System.out.println("Non ci sono progetti neutri");
                    }
                    break;

                case "SELEZIONA PROGETTISTI":
                    Set<Progetto> progettiPubblici = getProgettoController().allByIdProponenteAndStato(id,
                            StatoProgetto.PUBBLICO);
                    if (progettiPubblici.size() > 0) {
                        PrinterProgetti.printListaProgetti(progettiPubblici);
                        selezionaProgetto(StatoProgetto.PUBBLICO);
                    } else {
                        System.out.println("Non ci sono progetti pubblici");
                    }
                    break;

                case "CONFERMA PROGETTISTI":
                    Set<Progetto> progettiInValutazione = getProgettoController()
                            .allByIdProponenteAndStato(id, StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
                    if (progettiInValutazione.size() > 0) {
                        PrinterProgetti.printListaProgetti(progettiInValutazione);
                        selezionaProgetto(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
                    } else {
                        System.out.println("Non ci sono progetti in valutazione");
                    }
                    break;

                case "INVIA MESSAGGIO":
                    inviaMessaggio();
                    break;
                case "VISUALIZZA NOTIFICHE":
                    visualizzaMessaggi();
                    break;
                case "LOGOUT":
                    logout();
                    break;
            }
        }
    }

    public void startProgetto() {
        System.out.println("Benvenuto proponente: vuoi creare un nuovo progetto? \n" + "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            this.insertInfoProgetto();
        } else if (input.equals("N")) {
            System.out.println("Ok, nessun nuovo progetto creato");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }

    }

    public void insertInfoProgetto() {
        System.out.println("Inserire il nome del nuovo progetto");
        String nome = sc.nextLine();
        System.out.println("Inserire una descrizione per " + nome);
        String descrizione = sc.nextLine();

        Progetto progettoNeutro = getProgettoController().createProgetto(id, nome, descrizione);

        System.out.println("Nuovo progetto creato: ");
        System.out.println("Nome: " + nome);
        System.out.println("Descrizione: " + descrizione);
        System.out
                .println("Desideri consultare un esperto? Puo indicarti:\n" + "- Il numero dei progettisti necessari\n"
                        + "- Le competenze necessarie\n" + "- Il budget necessario\n" + "[Y] YES,    [N] NO");
        String yN = sc.nextLine().toUpperCase();
        if (yN.equals("Y")) {
            requestEsperto(progettoNeutro);
        } else if (yN.equals("N")) {
            insertInfoProgettisti(progettoNeutro);
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    public void requestEsperto(Progetto progettoNeutro) {
        getProgettoController().setStatoProgetto(progettoNeutro, StatoProgetto.IN_VALUTAZIONE_PROGETTO);
        System.out.println("Richiesta effettuata, sarai notificato quando almeno un esperto valuterà il progetto");
    }

    public void requestEsperto(Long idProgetto, Set<Candidatura> listaCandidature) {
        Set<Long> idsCandidature = new HashSet<Long>();
        getProgettoController().setStatoProgetto(idProgetto, StatoProgetto.IN_VALUTAZIONE_PROGETTO);
        for (Candidatura candidatura : listaCandidature) {
            idsCandidature.add(candidatura.getId());
        }
        System.out.println("Richiesta effettuata, sarai notificato quando almeno un esperto valuterà le candidature");
    }

    public void insertInfoProgettisti(Progetto progettoNeutro) {
        System.out.println("Inserire i progettisti richiesti e le loro specializzazioni separati da una virgola(,)\n"
                + "Esempio: CHIMICA, 2\n" + "Scrivere [DONE] per confermare\n"
                + "Specializzazioni attualmente disponibili: ");
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
                }
                else {
                    System.out.println("Inserire una specializzazione valida");
                }
            }
            catch(Exception e) {
                System.out.println("Inserire una specializzazione valida");
            }
            
        }

        getProgettoController().insertInfoProgettistiRichiesti(progettoNeutro, infoProgettistiRichiesti);
        System.out.println(progettoNeutro.getInfoProgettistiRichiesti().keySet());

        System.out.println("Il progetto " + progettoNeutro.getNome() + " è stato creato");
        PrinterProgetti.printInfoProgetto(progettoNeutro);
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
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public void selezionaProgetto(StatoProgetto stato) {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.toUpperCase().equals("EXIT")) {
            try {
                Long.valueOf(idProgetto);
                Progetto progetto = getProgettoController().one(Long.valueOf(idProgetto));
                if (!getProgettoController().checkProgetto(Long.valueOf(idProgetto), id, stato)) {
                    System.out.println("Inserisci un id valido");
                    selezionaProgetto(stato);
                }
                else {
                    PrinterProgetti.printInfoProgetto(progetto);

                    switch (progetto.getStatoProgetto()) {
                        case NEUTRO:
                            pubblicaProgetto(progetto);
                            break;
                        case PUBBLICO:
                            selezionaProgettisti(Long.valueOf(idProgetto));
                            break;
                        case IN_VALUTAZIONE_CANDIDATURE:
                            Set<Candidatura> progettistiPreselezionati = getCandidaturaController()
                                    .getCandidatureByIdProgettoAndStato(Long.valueOf(idProgetto), StatoCandidatura.PRESELEZIONATA);
                            if (progettistiPreselezionati.size() > 0) {
                                PrinterCandidature.printBasicCandidature(progettistiPreselezionati);
                                selezionaTeam(Long.valueOf(idProgetto));
                            }
                            else {
                                System.out.println("Non ci sono candidature preselezionate per il progetto con id " + idProgetto);
                            }
                            break;
                        default:
                            System.out.println("Impossibile processare l'operazione");
                            break;
                    }
                }
            }
             catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Inserisci un id valido");
                selezionaProgetto(stato);
            }
        }
    }

    public void selezionaProgettisti(Long idProgetto) {
        Set<Candidatura> progettistiCandidati = getCandidaturaController()
                .getCandidatureByIdProgettoAndStato(idProgetto, StatoCandidatura.DA_VALUTARE);
        if (progettistiCandidati.size() > 0) {
            System.out.println("Desideri che un esperto valuti le candidature?");
            System.out.println("[Y] YES,    [N] NO");
            String yN = sc.nextLine().toUpperCase();
            while (!yN.equals("EXIT")) {
                if (yN.equals("Y")) {
                    requestEsperto(idProgetto, progettistiCandidati);
                    return;
                } else if (yN.equals("N")) {
                    PrinterCandidature.printListaCandidature(idProgetto, StatoCandidatura.DA_VALUTARE);
                    selezionaCandidati(idProgetto);
                    return;
                }
            }
        }
        else {
            System.out.println("Non ci sono candidature per il progetto con id " + idProgetto);
        }
    }

    public void pubblicaProgetto(Progetto progetto) {
        System.out.println("Vuoi rendere il progetto " + progetto.getId() + " pubblico?");
        System.out.println("Se reso pubblico i progettisti possono iniziare a candidarsi");
        System.out.println("[Y] YES,    [N] NO");
        String yN = sc.nextLine().toUpperCase();
        if (yN.equals("Y")) {
            getProgettoController().pubblicaProgetto(progetto);
            System.out.println("Il progetto " + progetto.getId() + " è stato reso pubblico");
        } else if (yN.equals("N")) {
            System.out.println("Il progetto " + progetto.getId() + " non è stato reso pubblico");
        }
        else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    // PRESELEZIONE ILLIMITATA
    public void selezionaCandidati(Long idProgetto) {
        System.out.println("Si desidera preselezionare i progettisti?\n" + "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();

        if (input.equals("Y")) {
            System.out.println(
                    "Digita l'id della candidatura per visualizzare le informazioni del progettista desiderato,    [DONE] per terminare");

            while (true) {
                String idCandidaturaScelta = sc.nextLine();
                if (idCandidaturaScelta.toUpperCase().equals("DONE")) {
                    System.out.println("Selezione terminata \n");
                    break;
                }

                try {
                    Long.valueOf(idCandidaturaScelta);
                    Candidatura candidaturaScelta = getCandidaturaController().getCandidaturaById(Long.valueOf(idCandidaturaScelta));
                    if (candidaturaScelta != null) {

                        if (!candidaturaScelta.getStatoCandidatura().equals(StatoCandidatura.DA_VALUTARE)) {
                            System.out.println("Scegliere un id fra quelli proposti");
                        }
                        else {
                            PrinterProgettisti.printInfoProgettista(candidaturaScelta);
                            System.out.println("[Y] per salvare la candidatura se ritenuta interessante, o digita un altro id");
                            if (sc.nextLine().toUpperCase().equals("Y")) {
                                getCandidaturaController().modificaStatoCandidatura(Long.valueOf(idCandidaturaScelta),
                                    StatoCandidatura.PRESELEZIONATA);
                                System.out.println("Progettista " + candidaturaScelta.getIdProgettista() + " selezionato");
                            }
                        }
                       
                    } else {
                        System.out.println("Inserisci un id valido");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Inserisci un id valido");
                }
                
            }
            System.out.println(
                    "Si vuole passare alla selezione di un team definitivo di progettisti?\n" + "[Y] YES,    [N] NO");
            String yN = sc.nextLine().toUpperCase();
            if (yN.equals("Y")) {
                Set<Candidatura> candidaturePreselezionate = getCandidaturaController()
                        .getCandidatureByIdProgettoAndStato(Long.valueOf(idProgetto), StatoCandidatura.PRESELEZIONATA);
                if (candidaturePreselezionate.size() > 0) {
                    getProgettoController().setStatoProgetto(idProgetto, StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
                    // selezione definitiva team di progettisti
                    selezionaTeam(idProgetto);
                }
                else {
                    System.out.println("Non ci sono candidature preselezionate, impossibile procedere");
                }
                
            } else if (yN.equals("N")) {
                System.out.println("Non si vuole selezionare un team definitivo di progettisti");
            } else {
                System.out.println("Impossibile processare l'operazione");
            }

        } else if (input.equals("N")) {
            System.out.println("Non si vogliono selezionare candidati");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

    // SELEZIONE DEFINITIVA
    public void selezionaTeam(Long idProgetto) {
        System.out.println("Si desidera selezionare un team definitivo di progettisti?\n" + "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();

        if (input.equals("Y")) {
            PrinterCandidature.printListaCandidature(idProgetto, StatoCandidatura.PRESELEZIONATA);
            System.out.println("Digita l'id delle candidature da confermare");
            Set<Long> idsCandidatureSelezionate = new HashSet<>();
            int numSelezionati = 0;
            int numMassimo = getProgettoController().one(idProgetto).getNumeroProgettistiRichiesti();
            System.out.println("Devi selezionare " + numMassimo + " candidature");

            while (numSelezionati < numMassimo) {
                try {
                    Long idCandidatura = Long.valueOf(sc.nextLine());
                    Candidatura scelto = getCandidaturaController().getCandidaturaById(idCandidatura);
                    
                    if (scelto != null) {
                        if (!scelto.getStatoCandidatura().equals(StatoCandidatura.PRESELEZIONATA)) {
                            System.out.println("Scegliere un id fra quelli proposti");
                        }
                        else {
                            idsCandidatureSelezionate.add(scelto.getId());
                            numSelezionati++;
                            System.out.println("Progettista " + scelto.getIdProgettista() + " confermato");
                        }
                    } else {
                        System.out.println("Id progettista non valido");
                    }
                    
                }
                catch (NumberFormatException e) {
                    System.out.println("Inserire un id valido");
                }
            }
            System.out.println(
                    "Si desidera confermare la formazione di questo team di progettisti?\n" + "[Y] YES,    [N] NO");
            String yN = sc.nextLine().toUpperCase();
            if (yN.equals("Y")) {
                // selezione definitiva team di progettisti
                getCandidaturaController().modificaStatoCandidature(idsCandidatureSelezionate,
                        StatoCandidatura.ACCETTATA);
                getProgettoController().setStatoProgetto(idProgetto, StatoProgetto.FINALIZZATO);
                System.out.println("Complimenti hai trovato un team per il progetto!");
                // stampa le info di tutti i progettisti
                Set<Candidatura> candidatureSelezionate = new HashSet<Candidatura>();
                for (Long id : idsCandidatureSelezionate) {
                    Candidatura candidaturaTrovata = getCandidaturaController().getCandidaturaById(id);
                    candidatureSelezionate.add(candidaturaTrovata);
                }
                candidatureSelezionate.forEach(PrinterProgettisti::printInfoProgettista);
            } else if (yN.equals("N")) {
                System.out.println("Team non confermato");
            } else {
                System.out.println("Impossibile processare l'operazione");
            }

        } else if (input.equals("N")) {
            System.out.println("Non si vuole selezionare un team definitivo di progettisti");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }

}
