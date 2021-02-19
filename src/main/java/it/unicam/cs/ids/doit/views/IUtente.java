package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.gestori_utenti.UtenteController;
import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.utils.SpringContext;

import java.util.Scanner;

// TODO DA MODIFICARE
public class IUtente {
    Scanner sc;
    Long idUtente;

    public IUtente(Long idUtente) {
        this.sc = new Scanner(System.in);
        this.idUtente = idUtente;
    }

    private UtenteController getUtenteController() {
        return SpringContext.getBean(UtenteController.class);
    }

    private MessaggioController getMessaggioController() {
        return SpringContext.getBean(MessaggioController.class);
    }

    public void opzioniUtente(String input) {
//        while (true) {
//            System.out.println("Cosa vuoi fare\n" +
//                    "[INVIA MESSAGGIO]\n" +
//                    "[VISUALIZZA NOTIFICHE]");
//            String input = sc.nextLine().toUpperCase();
        switch (input) {
            case "INVIA MESSAGGIO":
                inviaMessaggio();
                break;
            case "VISUALIZZA NOTIFICHE":
                visualizzaMessaggi();
                break;
            case "EXIT":
                return;
        }
    }
//    }

    public void inviaMessaggio() {
        System.out.println("Seleziona un utente digitando l'id corrispondente");
        PrinterUtenti.printListaUtenti();
        Long idReceiver = Long.valueOf(sc.nextLine());
        //Destinatario non presente? Check dalla POST e try catch per gestire exception?
        System.out.println("Scrivi il testo del messaggio");
        String testo = sc.nextLine();
        // crea notifica
        // inserisci idSender, idReceiver, messaggio, letto=false
        // POST notifica
        getMessaggioController().createMessaggio(idUtente, idReceiver, testo);
        System.out.println("Messaggio inviato!");
    }

    public void visualizzaMessaggi() {
        PrinterMessaggi.printListaMessaggiNonLetti();
        // gestire nessun nuovo messaggio
        System.out.println("Si vogliono visualizzare anche i messaggi gia letti? [Y] [N]");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            PrinterMessaggi.printListaMessaggiLetti();
        }
    }


}
