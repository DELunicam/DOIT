package it.unicam.cs.ids.doit.views;

import java.util.Scanner;

import it.unicam.cs.ids.doit.gestori_utenti.UtenteController;
import it.unicam.cs.ids.doit.utenti.Ente;
import it.unicam.cs.ids.doit.utenti.Esperto;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utenti.Proponente;
import it.unicam.cs.ids.doit.utenti.Utente;
import it.unicam.cs.ids.doit.utils.SpringContext;

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
            System.out.println("Cosa vuoi fare\n" +
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
        System.out.println("Digita l'id di un progetto per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgetto = sc.nextLine();
            if (idProgetto.equalsIgnoreCase("EXIT")) {
                break;
            }
            PrinterProgetti.printInfoProgetto(Long.valueOf(idProgetto));
        }
    }

    public void visualizzaProgettisti() {
        PrinterProgettisti.printListaProgettisti();
        System.out.println("Digita l'id di un progettista per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgettista = sc.nextLine();
            if (idProgettista.equalsIgnoreCase("EXIT")) {
                break;
            }
            PrinterProgettisti.printInfoProgettista(Long.valueOf(idProgettista));
            // TODO session?
            //PrinterProgetti.printListaProgettiSvolti(Long.valueOf(idProgettista));
        }
    }

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
                // creaProgettista
                break;
            case "C":
                // creaEsperto
                break;
            case "D":
                // creaEnte
                break;
            case "EXIT":
                return;
        }

    }

    public void registraProponente() {
        String user = askUsername();
        while (getUtenteController().cercaUsername(user)) {
            System.out.println("Username " + user + " già in uso");
            user = askUsername();
        }
        String pass = askPassword();
        String mail = askMail();
        String nome = askNome();
        String cognome = askCognome();
        getUtenteController().creaProponente(user, pass, mail, nome, cognome);
        System.out.println("Coplimenti, ti sei registrato con successo");

    }



    public void autenticazione() {
        String user = askUsername();
        String pw = askPassword();
        Utente utente = getUtenteController().getUtente(user, pw);
        login(utente);
    }

    private void login(Utente utente) {
        if (utente instanceof Proponente) {
            System.out.println("SEI UN PROPONENTE");
            factory = new FactoryIProponente();
            factory.creaVista(utente);
            // vista proponente
        }
        else if (utente instanceof Progettista) {
            System.out.println("SEI UN PROGETTISTA");
            // vista progettista
        }
        else if (utente instanceof Esperto) {
            System.out.println("SEI UN ESPERTO");
            // vista progettista
        }
        else if (utente instanceof Ente) {
            System.out.println("SEI UN ENTE");
            // vista progettista
        }
        else {
            System.out.println("SEI UN UTENTE");
            // non è instance
        }

    }


    // HELPERS

    private String askUsername() {
        System.out.println("Inserisci il tuo username: ");
        String user = sc.nextLine();
        return user;
    }
    private String askPassword() {
        System.out.println("Inserisci la tua password: ");
        String pass = sc.nextLine();
        return pass;
    }
    private String askMail() {
        System.out.println("Inserisci il tuo indirizzo email: ");
        String mail = sc.nextLine();
        return mail;
    }
    private String askNome() {
        System.out.println("Inserisci il tuo nome: ");
        String nome = sc.nextLine();
        return nome;
    }
    private String askCognome() {
        System.out.println("Inserisci il tuo cognome: ");
        String cognome = sc.nextLine();
        return cognome;
    }

}
