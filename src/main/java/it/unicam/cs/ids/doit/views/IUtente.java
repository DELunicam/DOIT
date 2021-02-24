package it.unicam.cs.ids.doit.views;

import it.unicam.cs.ids.doit.notifica.MessaggioController;
import it.unicam.cs.ids.doit.utils.SpringContext;
import it.unicam.cs.ids.doit.utils.printers.PrinterMessaggi;
import it.unicam.cs.ids.doit.utils.printers.PrinterProgetti;
import it.unicam.cs.ids.doit.utils.printers.PrinterProgettisti;
import it.unicam.cs.ids.doit.utils.printers.PrinterUtenti;
import it.unicam.cs.ids.doit.views.factoryViews.FactoryIVisitatore;
import it.unicam.cs.ids.doit.views.printers.PrinterMessaggi;
import it.unicam.cs.ids.doit.views.printers.PrinterUtenti;

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

    public void inviaMessaggio() {
        System.out.println("Seleziona un utente digitando l'id corrispondente");
        PrinterUtenti.printListaUtenti();
        try {
            Long idReceiver = Long.valueOf(sc.nextLine());
            System.out.println("Scrivi il testo del messaggio");
            String testo = sc.nextLine();
            if (getMessaggioController().createMessaggio(id, idReceiver, testo).isPresent()) {
                System.out.println("Messaggio inviato!");
            } else {
                System.out.println("Destinatario non valido");
            }
        } catch (NumberFormatException e) {
            System.out.println("Id digitato non valido");
            inviaMessaggio();
        }

    }

    public void visualizzaMessaggi() {
        PrinterMessaggi.printListaMessaggiNonLetti(id);
        System.out.println("Si vogliono visualizzare anche i messaggi gia letti? [Y] [N]");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            PrinterMessaggi.printListaMessaggiLetti(id);
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

    public void logout() {
        System.out.println("Ti sei scollegato con successo!");
        final FactoryIVisitatore factory = new FactoryIVisitatore();
        factory.creaVista();
    }


}
