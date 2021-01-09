package it.unicam.cs.ids.views;


import it.unicam.cs.ids.progetto.GestoreProgetto;
import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.candidatura.Candidatura;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.candidatura.StatoCandidatura;

import java.util.*;


/**
 * cancellare ID nome sul diagramma delle classi
 */

public class IProponente {
    Scanner sc;
    GestoreProgetto gestore = new GestoreProgetto();
    String idProponente;

    public IProponente(String idProponente) {
        this.sc = new Scanner(System.in);
        this.idProponente = idProponente;
        //TODO IProponente
    }

    public void startProgetto(){
        //TODO ALMOST DONE startProgetto
        System.out.println("Benvenuto proponente: vuoi creare un nuovo progetto? \n" +
                "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();
        if(input.equals("Y")){
            this.insertInfoProgetto();
        } else if(input.equals("N")){
            System.out.println("Ok, nessun nuovo progetto creato");
        } else{
            System.out.println("Impossibile processare l'operazione");
        }


    }

    public void insertInfoProgetto(){
        //TODO ALMOST DONE insertInfoProgetto
        System.out.println("Inserire il nome del nuovo progetto");
        String nome = sc.nextLine();
        System.out.println("Inserire una descrizione per "+nome);
        String descrizione = sc.nextLine();

        Progetto progettoNeutro = gestore.createProgetto(idProponente, nome, descrizione);
        //Progetto progettoNeutro = new Progetto(name, description);

        System.out.println("Nuovo progetto creato: ");
        System.out.println("Nome: "+nome);
        System.out.println("Descrizione: "+descrizione);
        System.out.println("Desideri consultare un esperto? Puo indicarti:\n" +
                "- Il numero dei progettisti necessari\n" +
                "- Le competenze necessarie\n" +
                "- Il budget necessario\n" +
                "[Y] YES,    [N] NO");
        String yN = sc.nextLine().toUpperCase();
        if(yN.equals("Y")){
            requestEsperto(progettoNeutro);
        } else if(yN.equals("N")){
            insertInfoProgettisti(progettoNeutro);
        } else{
            System.out.println("Impossibile processare l'operazione");
        }
    }

    public void requestEsperto(Progetto progettoNeutro){
        //TODO requestEsperto
        progettoNeutro.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
        System.out.println("Richiesta effettuata, sarai notificato quando almeno un esperto valutera il progetto");
    }

    public void requestEsperto(Set<Candidatura> listaCandidature)
    {  
        for(Candidatura candidatura : listaCandidature)
        {
            gestore.getProgetto(candidatura.getIdProgetto()).setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
            candidatura.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);
        }
        System.out.println("Richieta effettuata, sarai notificato quando almeno un esperto valuterà le progettistiCandidati");
    }

    public void insertInfoProgettisti(Progetto progettoNeutro){
        //TODO insertInfoProgettisti
        //TODO modificare diagramma classi analisi, il metodo ha progettoNeutro come parametro
        System.out.println("Inserire i progettisti richiesti e le loro specializzazioni separati da una virgola(,)\n" +
                "Esempio: CHIMICA, 2\n" +
                "Scrivere [DONE] per confermare\n" +
                "Specializzazioni attualmente disponibili: ");
        for (Specializzazione s : Specializzazione.values()){
            System.out.print(s.toString()+", ");
        }
        System.out.println();

        Map<Specializzazione, Integer> infoProgettistiRichiesti = new HashMap<>();
        while(true){
            String input = sc.nextLine();
            if(input.equals("DONE")){
                break;
            }
            //TODO check stringa formato giusto
            String[] inputs = input.split(",");
            Specializzazione specProgettisti = Specializzazione.valueOf(inputs[0]);
            int numProgettisti = Integer.parseInt(inputs[1].trim());
            infoProgettistiRichiesti.put(specProgettisti, numProgettisti);
        }

        gestore.insertInfoProgettisti(progettoNeutro, infoProgettistiRichiesti);
        System.out.println(progettoNeutro.getInfoProgettistiRichiesti().keySet());

        System.out.println("Il progetto "+progettoNeutro.getNome()+" è stato creato");
        printInfoProgetto(progettoNeutro);
    }

    private void printInfoProgetto(Progetto progetto){
        System.out.println("Nome: "+progetto.getNome());
        System.out.println("Descrizione: "+progetto.getDescrizione());
        System.out.println("Stato: "+progetto.getStatoProgetto());
//        progetto.getInfoProgettistiRichiesti().forEach(entry -> {
//            System.out.println(entry.getKey()+" : "+entry.getValue() );
//        });

        //Map<Specializzazione, Integer> infoProgettistiRichiesti = progetto.getInfoProgettistiRichiesti();

        progetto.getInfoProgettistiRichiesti().forEach((key, value) -> System.out.println(key + " : " + value));
    }

    /**
     * Stampa in console le informazioni dei progetti creati dal proponente aventi un certo stato
     *
     * @param stato Lo stato del progetti progetti da visualizzare
     */
    public void viewProgetti(StatoProgetto stato){
        //TODO viewProgetti
        Set<Progetto> progettiTrovati = gestore.getListaProgetti(idProponente, stato);
        if(progettiTrovati.size()==0){
            System.out.println("Non ci sono progetti "+stato);
            return;
        }
        System.out.println("ID | NOME | DESCRIZIONE");
        for(Progetto p : progettiTrovati){
            System.out.println(p.getId()+" | "+p.getNome()+" | "+p.getDescrizione());
        }
        selezionaProgetto();
    }

    public void selezionaProgetto() {
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        if(!idProgetto.equals("EXIT")) {
            Progetto progetto = gestore.getProgetto(idProgetto);
            this.printInfoProgetto(progetto);
            // TODO merge con metodo di luca

            switch(progetto.getStatoProgetto()) {
                case NEUTRO:
                this.pubblicaProgetto(progetto.getId());
                    break;
                case PUBBLICO:
                    this.selezionaProgettisti(progetto.getId());
                    break; //
                case IN_VALUTAZIONE_CANDIDATURE:
                    this.viewCandidature(progetto.getId(), StatoCandidatura.PRESELEZIONATA);
                    break; //
                default:
                    System.out.println("Impossibile processare l'operazione");
                    break;
            }
        }
    }
    

    //public void selezionaProgettisti()
    public void selezionaProgettisti(String idProgetto)

    {
        
        System.out.println("Desideri che un esperto valuti le candidature?");
        System.out.println("[Y] YES,    [N] NO");
        String yN = sc.nextLine().toUpperCase();
        while (!yN.equals("EXIT"))
        {

            if (yN.equals("Y")) 
            {
                Set<Candidatura> progettistiCandidati = gestore.selezionaCandidatura(idProgetto, StatoCandidatura.DA_VALUTARE);
                this.requestEsperto(progettistiCandidati);
                return;
            }

            else if (yN.equals("N"))
            {
               viewCandidature(idProgetto, StatoCandidatura.DA_VALUTARE);
               return;
            }

        }
    }


    public void pubblicaProgetto(String idProgetto){
        //TODO pubblicaProgetto
        System.out.println("Vuoi rendere il progetto " + idProgetto + " pubblico?");
        System.out.println("Se reso pubblico i progettisti possono iniziare a candidarsi");
        System.out.println("[Y] YES,    [N] NO");
        String yN = sc.nextLine().toUpperCase();
        if (yN.equals("Y")) {
            gestore.getProgetto(idProgetto).setStatoProgetto(StatoProgetto.PUBBLICO);
            System.out.println("Il progetto " + idProgetto + " è stato reso pubblico");
        } else if (yN.equals("N")) {
            System.out.println("Il progetto " + idProgetto + " non è stato reso pubblico");
            return;
        }
    }

    public void viewCandidature(String idProgetto, StatoCandidatura statoCandidatura) {
        Set<Candidatura> progettistiCandidati = gestore.selezionaCandidatura(idProgetto, statoCandidatura);
        //StatoCandidatura statoCandidature;
        for (Candidatura candidatura : progettistiCandidati) {
            if (statoCandidatura.equals(candidatura.getStatoCandidatura())) {
                System.out.println("Candidato: " + candidatura.getIdProgettista() +
                ", stato candidatura: " + candidatura.getStatoCandidatura());
            }
        }
        if (statoCandidatura.equals(StatoCandidatura.PRESELEZIONATA)) {
            //statoCandidature = StatoCandidatura.PRESELEZIONATA;
            selezionaTeam(idProgetto);
        }
        else {
            //statoCandidature = StatoCandidatura.DA_VALUTARE;
            selezionaCandidati(idProgetto);
        }
        //this.selezionaCandidato(idProgetto, statoCandidature);
    }


    // PRESELEZIONE ILLIMITATA
    public void selezionaCandidati(String idProgetto) {
        System.out.println("Si desidera preselezionare i progettisti?\n" +
            "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();

        if (input.equals("Y")) {
            System.out.println("Digita l'id del progettista desiderato,    [DONE] per terminare");
            HashSet<Progettista> progettistiPreselezionati = new HashSet<Progettista>();
    
            while (!input.equals("DONE")) {
                String idProg = sc.nextLine();
                if (idProg.equals("DONE")) {
                        System.out.println ("Selezione terminata \n");
                        break;
                }
                Progettista scelto = gestore.getProgettista(idProg);
                if (scelto != null) {
                    progettistiPreselezionati.add(scelto);
                    for (Candidatura candidatura : gestore.getProgetto(idProgetto).getCandidature()) {
                        candidatura.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);
                    }
                    System.out.println("Progettista "+scelto.getNome() +" selezionato\n");
                }
                else {
                    System.out.println ("Id progettista non valido \n");
                }
            }
            this.viewInfoProgettista(progettistiPreselezionati);
            System.out.println("Si vuole passare alla selezione di un team definitivo di progettisti?\n" +
                "[Y] YES,    [N] NO");
            String yN = sc.nextLine().toUpperCase();
            if (yN.equals("Y")) {
                gestore.getProgetto(idProgetto).setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);
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
    public void selezionaTeam(String idProgetto) {
        System.out.println("Si desidera selezionare un team definitivo di progettisti?\n" +
            "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();

        if (input.equals("Y")) {
            System.out.println("Digita l'id del progettista desiderato");
            HashSet<Progettista> progettistiSelezionati = new HashSet<Progettista>();
            int numSelezionati = 0;
            int numMassimo = gestore.getProgetto(idProgetto).getNumeroProgettistiRichiesti();

            while (numSelezionati < numMassimo) {
                String idProg = sc.nextLine();
                Progettista scelto = gestore.getProgettista(idProg);
                if (scelto != null) {
                    progettistiSelezionati.add(scelto);
                    numSelezionati++;
                    System.out.println("Progettista "+scelto.getNome() +" confermato\n");
                }
                else {
                    System.out.println ("Id progettista non valido \n");
                }
            }
            this.viewInfoProgettista(progettistiSelezionati);
            System.out.println("Si desidera passare alla conferma del team definitivo di progettisti?\n" +
                "[Y] YES,    [N] NO");
            String yN = sc.nextLine().toUpperCase();
            if (yN.equals("Y")) {
                // selezione definitiva team di progettisti
                this.accettaCandidature(idProgetto, progettistiSelezionati);
            } else if (yN.equals("N")) {
                System.out.println("Non si vuole selezionare un team definitivo di progettisti");
            } else {
                System.out.println("Impossibile processare l'operazione");
            }

        } else if (input.equals("N")) {
            System.out.println("Team non confermato");
            // TODO svuota array e ricomincia a selezionare le progettistiCandidati buone
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }


    //public void viewInfoProgettista(Candidatura candidatura){
    public void viewInfoProgettista(Set<Progettista> progettisti) {
        //String info = gestore.getInfoProgettisti(progettisti);
        //System.out.println(info);
        for (Progettista progettista : progettisti) {
            System.out.println(
                "PROGETTISTA " + progettista.getId() + "\n" +
                "Nome: " + progettista.getNome() + "\n" +
                "Cognome: " + progettista.getCognome() + "\n" +
                "Specializzazioni: " + progettista.getSpecializzazioni() + "\n" +
                "Progetti svolti: " + progettista.getProgetti() + "\n" +
                "Mail: " + progettista.getMailAddress()
            );
        }
        //this.accettaCandidatura();
    }


    public void accettaCandidature(String idProgetto, Set<Progettista> progettistiSelezionati) {
        System.out.println("Si desidera confermare la formazione di questo team di progettisti?\n" +
        "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            // TODO invia notifica ai progettisti
            for (Progettista progettista : progettistiSelezionati) {
                for(Candidatura candidatura : gestore.getProgetto(idProgetto).getCandidature()) {
                    if (progettista.getId().equals(candidatura.getIdProgettista())) {
                        candidatura.setStatoCandidatura(StatoCandidatura.ACCETTATA);
                    }
                }
            }
            gestore.getProgetto(idProgetto).setStatoProgetto(StatoProgetto.FINALIZZATO);
            System.out.println("Complimenti hai trovato un team per il progetto!");
        } else if (input.equals("N")) {
            System.out.println("Team non confermato");
        } else {
            System.out.println("Impossibile processare l'operazione");
        }
    }


    public void seleziona(){
        //TODO seleziona
    }


}