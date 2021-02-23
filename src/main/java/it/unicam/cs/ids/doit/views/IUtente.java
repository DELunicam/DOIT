package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.utils.printers.PrinterMessaggi;
import it.unicam.cs.ids.doit.utils.printers.PrinterUtenti;
import it.unicam.cs.ids.doit.views.factoryViews.FactoryIVisitatore;

import java.util.Scanner;

public class IUtente {
    Scanner sc;
    Long id;

    public IUtente(Long id) {
        this.sc = new Scanner(System.in);
        this.id = id;
    }

    private MessaggioController getMessaggioController() {
        return SpringContext.getBean(MessaggioController.class);
    }

    public void opzioniUtente(String input) {
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
        if (getMessaggioController().createMessaggio(id, idReceiver, testo).isPresent()) {
            System.out.println("Messaggio inviato!");
        } else {
            System.out.println("Destinatario non valido");
        }
    }

    public void visualizzaMessaggi() {
        PrinterMessaggi.printListaMessaggiNonLetti(id);
        System.out.println("Si vogliono visualizzare anche i messaggi gia letti? [Y] [N]");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            PrinterMessaggi.printListaMessaggiLetti(id);
        }
    }

    public void logout() {
        System.out.println("Ti sei scollegato con successo!");
        final FactoryIVisitatore factory = new FactoryIVisitatore();
        factory.creaVista();
    }



}
