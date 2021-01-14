package it.unicam.cs.ids.views;

import it.unicam.cs.ids.progetto.GestoreProgetto;

import java.util.Scanner;

public class IVisitatore{//} implements PrinterProgettiInterface{
    Scanner sc;
    GestoreProgetto gestoreProgetto;

    public IVisitatore(GestoreProgetto gestoreProgetto) {
        this.sc = new Scanner(System.in);
        this.gestoreProgetto = gestoreProgetto;
    }

    //    public void visualizzaListaProgetti(){
//        //TODO visualizzaListaProgetti
//        Set<Progetto> progetti = gestoreProgetto.getListaProgetti();
//        System.out.println("ID, NOME, STATO");
//        for(Progetto progetto : progetti){
//            System.out.println(progetto.getId()+", "+progetto.getNome()+", "+progetto.getStatoProgetto());
//        }
//    }

    public void visualizzaListaProgetti() {
        PrinterProgetti.printListaProgetti();
        System.out.println("Digita l'id di un progetto per visualizzarne i dettagli, \n" +
                "EXIT per uscire");
        while (true) {
            String idProgetto = sc.nextLine();
            if (idProgetto.toUpperCase().equals("EXIT")) {
                break;
            }
            PrinterProgetti.printInfoProgetto(idProgetto);
        }
    }

    //    // Metodo quasi identico a printInfoProgetto di IProponente
//    public void visualizzaInfoProgetto(String idProgetto){
//        //TODO visualizzaInfoProgetto
//        Progetto progetto = gestoreProgetto.getProgetto(idProgetto);
//        System.out.println("IdProgetto: "+progetto.getId());
//        System.out.println("Nome: "+progetto.getNome());
//        System.out.println("Descrizione: "+progetto.getDescrizione());
//        System.out.println("Stato: "+progetto.getStatoProgetto());
//        System.out.println("IdProponente: "+progetto.getStatoProgetto());
//        System.out.println("Progettisti richiesti: \n" +
//                "SPECIALIZZAZONE | NUMERO");
//        progetto.getInfoProgettistiRichiesti().forEach((key, value) -> System.out.println(key + " : " + value));
//    }
//    public void visualizzaInfoProgetto(String idProgetto) {
//        PrinterProgetti.printInfoProgetto(idProgetto);
//    }

//    public void visualizzaProgettisti() {
//        PrinterProgettisti.printListaProgettisti();
//        System.out.println("Digita l'id di un progettista per visualizzarne i dettagli, \n" +
//                "EXIT per uscire");
//        while (true) {
//            String idProgettista = sc.nextLine();
//            if (idProgettista.toUpperCase().equals("EXIT")) {
//                break;
//            }
//            PrinterProgettist.printInfoProgettista(idProgettista);
//        }
//    }
}
