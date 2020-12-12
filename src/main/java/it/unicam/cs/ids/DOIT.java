package it.unicam.cs.ids;

import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.views.IProponente;

import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IProponente proponente = new IProponente("PROP1");
        //proponente.startProgetto();
        
        // conferma progettisti definitivi
        proponente.viewProgetti(StatoProgetto.IN_ATTESA);
    }
}
