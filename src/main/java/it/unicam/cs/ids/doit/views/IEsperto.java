package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.GestoreCandidature;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.valutazione.GestoreValutazioni;

import java.util.*;


// aggiunto visualizzaCandidature(String idProgetto)
// rimosso approvaCandidato()
// cambiato selezionaProposta(String idProgetto) in visualizzaProgetto(String idProgetto)
// cambiato selezionaCandidatura(String idProgettista) in visualizzaInfoProgettista(String idProgettista)

public class IEsperto {
    Scanner sc;
    GestoreValutazioni gestoreValutazioni = GestoreValutazioni.getInstance();
    GestoreCandidature gestoreCandidature = GestoreCandidature.getInstance();
    String idEsperto;

    public IEsperto(String idEsperto) {
        this.sc = new Scanner(System.in);
        this.idEsperto = idEsperto;
    }

    public void opzioniDisponibili(){
        while (true) {
            System.out.println("Cosa vuoi fare\n" +
                    "[VALUTA PROPOSTA]\n" +
                    "[VALUTA PROGETTISTI]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "VALUTA PROPOSTA":
                    valutaProposta();
                    break;
                case "VALUTA PROGETTISTI":
                    valutaProgettisti();
                    break;
                case "EXIT":
                    return;
            }
        }
    }

    // CASO D'USO
    public void valutaProposta() {
        // TODO controllare tutti gli input
        System.out.println("Vuoi valutare una proposta di progetto? \n" +
                "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            PrinterProgetti.printListaProgetti(StatoProgetto.IN_VALUTAZIONE_PROGETTO);

            System.out.println("Digitare l'id del progetto per selezionarlo e visualizzare i dettagli, [EXIT] per uscire");
            String idProgetto = sc.nextLine();
            if (!idProgetto.equals("EXIT")) {
                PrinterProgetti.printInfoProgetto(idProgetto);
                System.out.println("Si vuole valutare questa proposta di progetto? \n" +
                        "[Y] YES,    [N] NO");
                String conferma = sc.nextLine().toUpperCase();

                if (conferma.equals("Y")) {
                    System.out.println("Si ritiene realizzabile questa proposta di progetto? \n" +
                            "[Y] YES,    [N] NO");
                    String fattibile = sc.nextLine().toUpperCase();
                    if (fattibile.equals("Y")) {
                        gestoreValutazioni.createValutazione(idProgetto, idEsperto, this.requestProgettistiECompetenze());
                        System.out.println("Valutazione completa inviata \n");
                    } else {
                        gestoreValutazioni.createValutazione(idProgetto, idEsperto);
                        System.out.println("Valutazione negativa inviata \n");
                    }
                } else {
                    System.out.println("Digitare l'id di un altro progetto, [EXIT] per uscire");
                    idProgetto = sc.nextLine();
                }
            }

        } else if (input.equals("N")) {
            System.out.println("Non si vogliono valutare proposte di progetto \n");
            return;
        } else {
            System.out.println("Impossibile processare l'operazione \n");
            return;
        }
    }

    // CASO D'USO
    public void valutaProgettisti() {
        Set<Candidatura> consigliate = new HashSet<Candidatura>();
        Set<Candidatura> sconsigliate = new HashSet<Candidatura>();
        // TODO controllare tutti gli input
        // TODO check specializzioni dell'esperto
        System.out.println("Vuoi valutare i progettisti candidati ad un progetto? \n" +
                "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            PrinterProgetti.printListaProgetti(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);

            System.out.println("Digitare l'id del progetto per selezionarlo e visualizzare i dettagli, [EXIT] per uscire");
            String idProgetto = sc.nextLine();
            if (!idProgetto.equals("EXIT")) {
                System.out.println("Dettagli progetto " + idProgetto + ":");
                PrinterProgetti.printInfoProgetto(idProgetto);
                System.out.println("Candidature al progetto " + idProgetto + ":");
                PrinterCandidature.printListaCandidature(idProgetto, StatoCandidatura.DA_VALUTARE);
                System.out.println("Digitare l'id del progettista di cui si vogliono visualizzare i dettagli, [DONE] per uscire");
                while (true) {
                    String idInput = sc.nextLine();
                    if (idInput.equals("DONE")) {
                        break;
                    }
                    PrinterProgettisti.printInfoProgettista(idInput);
                    System.out.println("Si vuole consigliare questo progettista per lavorare al progetto? \n" +
                            "[Y] YES,    [N] NO");
                    String conferma = sc.nextLine().toUpperCase();
                    if (conferma.equals("Y")) {
                        Candidatura candidatura = gestoreCandidature.getCandidatura(idProgetto, idInput);
                        gestoreCandidature.addCandidatura(candidatura, consigliate);
                    } else if (conferma.equals("N")) {
                        Candidatura candidatura = gestoreCandidature.getCandidatura(idProgetto, idInput);
                        gestoreCandidature.addCandidatura(candidatura, sconsigliate);
                    } else {
                        System.out.println("Impossibile eseguire l'operazione");
                    }
                    System.out.println("Inserisci l'id del prossimo progettista");
                }

                System.out.println("Si vogliono confermare le valutazioni sui progettisti candidati? \n" +
                        "[Y] YES,    [N] NO");
                String scelta = sc.nextLine().toUpperCase();
                if (scelta.equals("Y")) {
                    gestoreCandidature.confermaSelezione(idEsperto, consigliate, sconsigliate);
                    System.out.println("Valutazioni inviate");
                } else if (scelta.equals("N")) {
                    System.out.println("Valutazioni non inviate");
                    return;
                } else {
                    System.out.println("Impossibile eseguire l'operazione");
                    return;
                }

            }

        } else if (input.equals("N")) {
            System.out.println("Non si vogliono valutare proposte di progetto \n");
            return;
        } else {
            System.out.println("Impossibile processare l'operazione \n");
            return;
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
            //TODO check stringa formato giusto
            String[] inputs = input.split(",");
            Specializzazione specProgettisti = Specializzazione.valueOf(inputs[0]);
            int numProgettisti = Integer.parseInt(inputs[1].trim());
            infoProgettistiRichiesti.put(specProgettisti, numProgettisti);
        }

        return infoProgettistiRichiesti;
    }


}