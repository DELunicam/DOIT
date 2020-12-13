package it.unicam.cs.ids.views;


import it.unicam.cs.ids.utils.GestoreProgetto;
import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.Candidatura;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utenti.Progettista;
import it.unicam.cs.ids.utenti.Proponente;
import it.unicam.cs.ids.progetto.StatoCandidatura;

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
        System.out.println("Richieta effettuata, sarai notificato quando almeno un esperto valuterà le candidature");
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
        Set<Progetto> progettiNeutri = gestore.getListaProgetti(idProponente, stato);
        if(progettiNeutri.size()==0){
            System.out.println("Non ci sono progetti "+stato);
            return;
        }
        System.out.println("ID | NOME | DESCRIZIONE");
        for(Progetto p : progettiNeutri){
            System.out.println(p.getId()+" | "+p.getNome()+" | "+p.getDescrizione());
        }
        selezionaProgetto();
    }

    public void selezionaProgetto(){
        //TODO selezionaProgetto
        System.out.println("Digitare l'id del progetto per visualizzare i dettagli, [EXIT] per uscire");
        String idProgetto = sc.nextLine();
        while(!idProgetto.equals("EXIT")) {
            Progetto progetto = gestore.getProgetto(idProgetto);
            this.printInfoProgetto(progetto);
            // TODO merge con metodo di luca
            System.out.println("Se si desiderano informazioni su progettisti digitare [PROGETTISTI]\n" +
                    "Se si desiderano informazioni sulle candidature digitare [CANDIDATURE]");
            String dettagli = sc.nextLine().toUpperCase();
            if (dettagli.equals("PROGETTISTI")) {
                // TODO viewProgettisti() o qualcosa di simile
            } else if (dettagli.equals("CANDIDATURE")) {
                this.viewCandidature(progetto.getId(), progetto.getStatoProgetto());
            } else {
                System.out.println("Impossibile processare l'operazione");
            }
        }
    }

    public void selezionaProgettisti()

    {

        
        System.out.print("Digitare l'id del progetto per cui si vuole selezionare progettisti, [EXIT] per uscire\n");
        String  idProgetto = sc.next();
        System.out.println("Desideri che un esperto valuti le candidature? \n ");
            System.out.println("[Y] YES,    [N] NO");
        while(!idProgetto.equals("EXIT"))
        {

            String yN = sc.nextLine().toUpperCase();
            if (yN.equals("Y")) 
            {
                Set<Candidatura> candidature = gestore.selezionaCandidatura(idProgetto, StatoProgetto.PUBBLICO);
                this.requestEsperto(candidature);
                return;
            }

            else if (yN.equals("N"))
            {
               viewCandidature(idProgetto, StatoProgetto.PUBBLICO);
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
        } else if (yN.equals("N")) {
            return;
        }
    }

    public void viewCandidature(String idProgetto, StatoProgetto stato) {
        Set<Candidatura> candidature = gestore.selezionaCandidatura(idProgetto, stato);
        for (Candidatura candidatura : candidature) {
            System.out.println("Candidato: " + candidatura.getIdProgettista() +
            ", stato candidatura: " + candidatura.getStatoCandidatura());
        }
        this.selezionaCandidato(idProgetto);
        
    }

    // TODO controllare
    public void selezionaCandidato(String idProgetto) {
        System.out.println("Si desidera selezionare un team di progettisti?\n" +
        "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();

        if (input.equals("Y")) {
            System.out.println("Digita l'id del progettista desiderato");
            HashSet<Progettista> candidature = new HashSet<Progettista>();
            int numSelezionati = 0;
            int numMassimo = gestore.getProgetto(idProgetto).getNumeroProgettistiRichiesti();

            while (numSelezionati < numMassimo) {
                String idProg = sc.nextLine();
                Progettista scelto = gestore.getProgettista(idProg);
                if (scelto != null) {
                    candidature.add(scelto);
                    numSelezionati++;
                    System.out.println("Progettista "+scelto.getNome() +" confermato\n");
                }
                else 
                {
                    System.out.println ("Id progettista non valido \n");
                }
            }
            this.viewInfoProgettista(candidature);

        } else if (input.equals("N")) {
            System.out.println("Team non confermato");
            // TODO svuota array e ricomincia a selezionare le candidature buone
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
        this.accettaCandidatura();
    }

    public void accettaCandidatura(){
        System.out.println("Si desidera confermare la formazione di questo team di progettisti?\n" +
        "[Y] YES,    [N] NO");
        String input = sc.nextLine().toUpperCase();
        if (input.equals("Y")) {
            // TODO invia notifica ai progettisti e svuota array
            // TODO cambia stato progetto/candidature
            System.out.println("Complimenti hai trovato un team per il progetto!");
        } else if (input.equals("N")) {
            System.out.println("Team non confermato");
            // TODO svuota array e ricomincia a selezionare le candidature buone
        } else {
            System.out.println("Impossibile processare l'operazione");
        }

    }

    public void seleziona(){
        //TODO seleziona
    }






}
