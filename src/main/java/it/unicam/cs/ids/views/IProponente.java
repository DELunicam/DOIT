package it.unicam.cs.ids.views;


import it.unicam.cs.ids.utils.GestoreProgetto;
import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.Candidatura;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;

import java.util.*;

/**
 * cancellare ID nome sul diagramma delle classi
 */

public class IProponente {
    Scanner sc;
    public GestoreProgetto gestore = new GestoreProgetto();
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
            System.out.println("Impossibile processare l'operazione'");
        }
    }

    public void requestEsperto(Progetto progettoNeutro){
        //TODO requestEsperto
        System.out.println("Richiesta effettuata, sarai notificato quando almeno un esperto valutera il progetto");
    }

    public void requestEsperto(List<Candidatura> listaCandidature){
        //TODO requestEsperto
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
            this.printInfoProgetto(gestore.getProgetto(idProgetto));
            // TODO merge con metodo di luca
        }
    }

    public void selezionaProgettisti(){
        //TODO selezionaProgettisti
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

    public void selezionaProgettista(){
        //TODO selezionaProgettista
    }

    public void viewCandidatura(String id){
        //TODO viewCandidatura
    }

    public void viewInfoProgettista(Candidatura candidatura){
        //TODO viewInfoProgettista
    }

    public void accettaCandidatura(){
        //TODO accettaCandidatura
    }

    public void seleziona(){
        //TODO seleziona
    }






}
