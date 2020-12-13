package it.unicam.cs.ids.views;


import it.unicam.cs.ids.utils.GestoreProgetto;
import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.StatoCandidatura;
import it.unicam.cs.ids.progetto.Candidatura;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;

import java.util.*;

/**
 * cancellare ID nome sul diagramma delle classi
 */

public class IProponente {
    Scanner sc;
    GestoreProgetto gestore = new GestoreProgetto();

    public IProponente() {
        this.sc = new Scanner(System.in);
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

        Progetto progettoNeutro = gestore.createProgetto(nome, descrizione);
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

    public void requestEsperto(Progetto progettoNeutro)
    {
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

        //TODO questo sara compito del gestore che prendera come argomento il progetto e la map
        gestore.insertInfoProgettisti(progettoNeutro, infoProgettistiRichiesti);
        System.out.println(progettoNeutro.getInfoProgettistiRichiesti().keySet());
        //

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

    public void viewProgetti(StatoProgetto stato){
        //TODO viewProgetti
    }

    public void selezionaProgetto(String id){
        //TODO selezionaProgetto
    }

    public void selezionaProgettisti()
    
    {
        
        //TODO selezionaProgettisti
       ////viewCandidature(idProgetto, stato);
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
            }

            else if (yN.equals("N"))
            {
               viewCandidature(idProgetto, StatoProgetto.PUBBLICO);
            }
        

        }
    }

    public void pubblicaProgetto(){
        //TODO pubblicaProgetto
    }

    public void selezionaProgettista()
    //TODO serve?
    {
       
    }

<<<<<<< Updated upstream
    public void viewCandidatura(String id){
        //TODO viewCandidatura
=======
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
            System.out.println("Digita il nome del progettista desiderato");
            HashSet<Progettista> candidature = new HashSet<Progettista>();
            int numSelezionati = 0;
            int numMassimo = gestore.getProgetto(idProgetto).getNumeroProgettistiRichiesti();

            while (numSelezionati < numMassimo -1) {
                String idProg = sc.nextLine();
                Progettista scelto = gestore.getProgettista(idProg);
                if (scelto != null) {
                    candidature.add(scelto);
                    System.out.println("Progettista confermato\n");
                    numSelezionati++;
                    
                }
                else
                {
                    System.out.println("Id progettista non valido\n");
                }
            }
            this.viewInfoProgettista(candidature);

        } else if (input.equals("N")) {
            System.out.println("Team non confermato");
            // TODO svuota array e ricomincia a selezionare le candidature buone
        } else {
            System.out.println("Impossibile processare l'operazione");
        }

>>>>>>> Stashed changes
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
