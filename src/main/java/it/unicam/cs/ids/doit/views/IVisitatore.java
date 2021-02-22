package it.unicam.cs.ids.doit.views;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import it.unicam.cs.ids.doit.gestori_utenti.UtenteController;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.*;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.views.factoryViews.*;

public class IVisitatore {
    Scanner sc;
    private FactoryVista factory;

    private UtenteController getUtenteController() {
        return SpringContext.getBean(UtenteController.class);
    }

    public IVisitatore() {
        this.sc = new Scanner(System.in);
    }

    public void opzioniDisponibili() {
        while (true) {
            System.out.println("Cosa vuoi fare?\n" +
                "[VISUALIZZA PROGETTI]\n" +
                "[VISUALIZZA PROGETTISTI]\n" +
                "[REGISTRAZIONE]\n" +
                "[LOGIN]");
            String input = sc.nextLine().toUpperCase();
            switch (input) {
                case "VISUALIZZA PROGETTI":
                    visualizzaListaProgetti();
                    break;
                case "VISUALIZZA PROGETTISTI":
                    visualizzaProgettisti();
                    break;
                case "REGISTRAZIONE":
                    registrazione();
                    break;
                case "LOGIN":
                    autenticazione();
                    break;
                case "EXIT":
                    return;
            }
        }
    }

    public void visualizzaListaProgetti() {
        PrinterProgetti.printListaProgetti();
        System.out.println("Digita l'id di un progetto per visualizzarne i dettagli, [EXIT] per uscire");
        while (true) {
            String idProgetto = sc.nextLine();
            if (idProgetto.equalsIgnoreCase("EXIT")) {
                break;
            }
            else {
                try {
                    Long.valueOf(idProgetto);
                }
                catch (NumberFormatException e) {
                    System.out.println("Inserisci un id valido");
                    visualizzaListaProgetti();
                    break;
                }
                PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
            }
        }      
    }

    public void visualizzaProgettisti() {
        PrinterProgettisti.printListaProgettisti();
        System.out.println("Digita l'id di un progettista per visualizzarne i dettagli, [EXIT] per uscire");
        while (true) {
            String idProgettista = sc.nextLine();
            if (idProgettista.equalsIgnoreCase("EXIT")) {
                break;
            }
            else {
                try {
                    Long.valueOf(idProgettista);
                }
                catch (NumberFormatException e) {
                    System.out.println("Inserisci un id valido");
                    visualizzaProgettisti();
                    break;
                }
                PrinterProgettisti.printInfoProgettista(Long.valueOf(idProgettista));
                //PrinterProgetti.printListaProgettiSvolti(Long.valueOf(idProgettista));
            }
        }
    }

    // SIGNUP

    public void registrazione() {
        System.out.println("Digita il tipo di registrazione che vuoi effettuare\n" +
            "[A] per PROPONENTE\n" +
            "[B] per PROGETTISTA\n" +
            "[C] per ESPERTO\n" +
            "[D] per ENTE\n" +
            "[EXIT] per uscire");
        String input = sc.nextLine().toUpperCase();
        switch (input) {
            case "A":
                registraProponente();
                break;
            case "B":
                registraProgettista();
                break;
            case "C":
                registraEsperto();
                break;
            case "D":
                registraEnte();
                break;
            case "EXIT":
                return;
            default:
                System.out.println("Per favore inserire una delle opzioni proposte");
                registrazione();
        }

    }

    public void registraProponente() {
        String user = askUsername();
        checkUsername(user);
        String pass = askPassword();
        String mail = askMail();
        String nome = askNome();
        String cognome = askCognome();
        getUtenteController().creaProponente(user, pass, mail, nome, cognome);
        System.out.println("Complimenti, ti sei registrato con successo");
    }

    public void registraProgettista() {
        String user = askUsername();
        checkUsername(user);
        String pass = askPassword();
        String mail = askMail();
        String nome = askNome();
        String cognome = askCognome();
        Set<Specializzazione> specializzazioni = askSpecializzazioni();
        getUtenteController().creaProgettista(user, pass, mail, nome, cognome, specializzazioni);
        System.out.println("Complimenti, ti sei registrato con successo");
    }

    public void registraEsperto() {
        String user = askUsername();
        checkUsername(user);
        String pass = askPassword();
        String mail = askMail();
        String nome = askNome();
        String cognome = askCognome();
        Set<Specializzazione> specializzazioni = askSpecializzazioni();
        getUtenteController().creaEsperto(user, pass, mail, nome, cognome, specializzazioni);
        System.out.println("Complimenti, ti sei registrato con successo");
    }

    public void registraEnte() {
        String user = askUsername();
        checkUsername(user);
        String pass = askPassword();
        String mail = askMail();
        String nome = askNome();
        String tipologia = askTipologia();
        String descrizione = askDescrizione();
        getUtenteController().creaEnte(user, pass, mail, nome, tipologia, descrizione);
        //TODO aggiungere lavoratori
        System.out.println("Complimenti, ti sei registrato con successo");
    }


    // LOGIN

    public void autenticazione() {
        String user = askUsername();
        String pw = askPassword();
        Utente utente = getUtenteController().getUtente(user, pw);
        if (utente != null) {
            login(utente);
        }
        else {
            System.out.println("Credenziali sbagliate");
        }
    }

    private void login(Utente utente) {
        if (utente instanceof Proponente) {
            // vista proponente
            System.out.println("Benvenuto proponente " + utente.getUsername());
            factory = new FactoryIProponente();
        }
        else if (utente instanceof Progettista) {
            // vista progettista
            System.out.println("Benvenuto progettista " + utente.getUsername());
            factory = new FactoryIProgettista();
        }
        else if (utente instanceof Esperto) {
            // vista esperto
            System.out.println("Benvenuto esperto " + utente.getUsername());
            factory = new FactoryIEsperto();
        }
        else if (utente instanceof Ente) {
            // vista ente
            System.out.println("Benvenuto ente " + utente.getUsername());
            factory = new FactoryIEnte();
        }
        else {
            System.out.println("Benvenuto utente " + utente.getUsername());
            // non è instance -> non è un utente registrato -> non si dovrebbe attivare
            autenticazione();
        }

        if (factory != null) {
            factory.creaVista(utente);
        }

    }


    // ASK FIELD

    private String askUsername() {
        System.out.println("Inserisci il tuo username:");
        String user = sc.nextLine();
        return user;
    }
    private String askPassword() {
        System.out.println("Inserisci la tua password:");
        String pass = sc.nextLine();
        return pass;
    }
    private String askMail() {
        System.out.println("Inserisci il tuo indirizzo email:");
        String mail = sc.nextLine();
        return mail;
    }
    private String askNome() {
        System.out.println("Inserisci il tuo nome:");
        String nome = sc.nextLine();
        return nome;
    }
    private String askCognome() {
        System.out.println("Inserisci il tuo cognome:");
        String cognome = sc.nextLine();
        return cognome;
    }
    public Set<Specializzazione> askSpecializzazioni() {
        Set<Specializzazione> specInserite = new HashSet<Specializzazione>();
        System.out.println("Inserisci le tue specializzazioni:\n" +
                "Scrivere [DONE] per confermare\n" +
                "Specializzazioni attualmente disponibili:");
        for (Specializzazione specDisponibile : Specializzazione.values()) {
            System.out.print(" - " + specDisponibile.toString());
        }
        System.out.println();
        while (true) {
            String input = sc.nextLine();
            if (checkSpecializzazione(input)) {
                specInserite.add(Specializzazione.valueOf(input));
            }
            else if (input.equals("DONE")) {
                break;
            }
            else {
                System.out.println("Specializzazione [" + input + "] non valida");
            }
        }
        return specInserite;
    }
    private String askTipologia() {
        System.out.println("Inserisci il settore in cui operi:");
        String tipologia = sc.nextLine();
        return tipologia;
    }
    private String askDescrizione() {
        System.out.println("Inserisci una breve descrizione:");
        String descrizione = sc.nextLine();
        return descrizione;
    }

    // CHECK FIELD

    private void checkUsername(String user) {
        while (getUtenteController().cercaUsername(user)) {
            System.out.println("Username " + user + " già in uso");
            user = askUsername();
        }
    }

    private boolean checkSpecializzazione(String specInserita) {
        for (Specializzazione specDisponibile : Specializzazione.values()) {
            if (specInserita.equals(specDisponibile.toString())) {
                return true;
            }
        }
        return false;
    }

}
