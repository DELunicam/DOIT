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
import java.util.Set;

public class IProponente extends IUtente /*implements Vista */{

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
            System.out.println("Cosa vuoi fare?\n" + "[CREA]\n" + "[PUBBLICA]\n" + "[SELEZIONA PROGETTISTI]\n"
                    + "[CONFERMA PROGETTISTI]\n" + "[LOGOUT]");
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
                        selezionaProgetto();
                    } else {
                        System.out.println("Non ci sono progetti neutri");
                    }
                    break;

                case "SELEZIONA PROGETTISTI":
                    Set<Progetto> progettiPubblici = getProgettoController().allByIdProponenteAndStato(id,
                            StatoProgetto.PUBBLICO);
                    if (progettiPubblici.size() > 0) {
                        PrinterProgetti.printListaProgetti(progettiPubblici);
                        selezionaProgetto();
                    } else {
                        System.out.println("Non ci sono progetti pubblici");
                    }
                    break;

                case "CONFERMA PROGETTISTI":
                    Set<Progetto> progettiInValutazione = getProgettoController()
                            .allByIdProponenteAndStato(id, StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
                    if (progettiInValutazione.size() > 0) {
                        PrinterProgetti.printListaProgetti(progettiInValutazione);
                        selezionaProgetto();
                    } else {
                        System.out.println("Non ci sono progetti in valutazione");
                    }
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
        getCandidaturaController().modificaStatoCandidature(idsCandidature, StatoCandidatura.DA_VALUTARE);
        System.out.println(
                "Richiesta effettuata, sarai notificato quando almeno un esperto valuterà le progettistiCandidati");
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
            // TODO check stringa formato giusto
            String[] inputs = input.split(",");
            Specializzazione specProgettisti = Specializzazione.valueOf(inputs[0]);
            int numProgettisti = Integer.parseInt(inputs[1].trim());
            infoProgettistiRichiesti.put(specProgettisti, numProgettisti);
        }

        getProgettoController().insertInfoProgettistiRichiesti(progettoNeutro, infoProgettistiRichiesti);
        System.out.println(progettoNeutro.getInfoProgettistiRichiesti().keySet());

        System.out.println("Il progetto " + progettoNeutro.getNome() + " è stato creato");
        PrinterProgetti.printInfoProgetto(progettoNeutro);
    }

    public void selezionaProgetto() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if (!idProgetto.equals("EXIT")) {
            Progetto progetto = getProgettoController().one(Long.valueOf(idProgetto));
            PrinterProgetti.printInfoProgetto(progetto);

            switch (progetto.getStatoProgetto()) {
                case NEUTRO:
                    this.pubblicaProgetto(progetto);
                    break;
                case PUBBLICO:
                    this.selezionaProgettisti(Long.valueOf(idProgetto));
                    break;
                case IN_VALUTAZIONE_CANDIDATURE:
                    PrinterCandidature.printListaCandidature(Long.valueOf(idProgetto), StatoCandidatura.PRESELEZIONATA);
                    selezionaTeam(Long.valueOf(idProgetto));
                    break;
                default:
                    System.out.println("Impossibile processare l'operazione");
                    break;
            }
        }
    }

    public void selezionaProgettisti(Long idProgetto) {
        System.out.println("Desideri che un esperto valuti le candidature?");
        System.out.println("[Y] YES,    [N] NO");
        String yN = sc.nextLine().toUpperCase();
        while (!yN.equals("EXIT")) {

            if (yN.equals("Y")) {
                Set<Candidatura> progettistiCandidati = getCandidaturaController()
                        .getCandidatureByIdProgettoAndStato(idProgetto, StatoCandidatura.DA_VALUTARE);
                requestEsperto(idProgetto, progettistiCandidati);
                return;
            } else if (yN.equals("N")) {
                PrinterCandidature.printListaCandidature(idProgetto, StatoCandidatura.DA_VALUTARE);
                selezionaCandidati(idProgetto);
                return;
            }
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
                if (idCandidaturaScelta.equals("DONE")) {
                    System.out.println("Selezione terminata \n");
                    break;
                }
                Candidatura candidaturaScelta = getCandidaturaController()
                        .getCandidaturaById(Long.valueOf(idCandidaturaScelta));
                if (candidaturaScelta != null) {
                    PrinterProgettisti.printInfoProgettista(candidaturaScelta);
                    System.out.println("[Y] per salvare la candidatura se ritenuta interessante");
                    if (sc.nextLine().equals("Y")) {
                        getCandidaturaController().modificaStatoCandidatura(Long.valueOf(idCandidaturaScelta),
                                StatoCandidatura.PRESELEZIONATA);
                        System.out.println("Progettista " + candidaturaScelta.getIdProgettista() + " selezionato\n");
                    }
                } else {
                    System.out.println("Id progettista non valido \n");
                }
            }
            System.out.println(
                    "Si vuole passare alla selezione di un team definitivo di progettisti?\n" + "[Y] YES,    [N] NO");
            String yN = sc.nextLine().toUpperCase();
            if (yN.equals("Y")) {
                getProgettoController().setStatoProgetto(idProgetto, StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
                // selezione definitiva team di progettisti
                selezionaTeam(idProgetto);
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

            while (numSelezionati < numMassimo) {
                Long idCandidatura = Long.valueOf(sc.nextLine());
                Candidatura scelto = getCandidaturaController().getCandidaturaById(idCandidatura);
                if (scelto != null) {
                    idsCandidatureSelezionate.add(scelto.getId());
                    numSelezionati++;
                    System.out.println("Progettista " + scelto.getIdProgettista() + " confermato\n");
                } else {
                    System.out.println("Id progettista non valido \n");
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
