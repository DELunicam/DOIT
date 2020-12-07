package it.unicam.cs.ids;

import it.unicam.cs.ids.views.IProponente;

import java.util.Scanner;

public class DOIT {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IProponente proponente = new IProponente();
        proponente.startProgetto();
    }
}
