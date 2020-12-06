package it.unicam.cs.ids.utils;

import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utenti.Progettista;

import java.util.*;

public class GestoreProgetto {
    private Set<Progetto> progetti;
    public GestoreProgetto(){
        this.progetti = new HashSet<>();
    }
    public GestoreProgetto(Set<Progetto> progetti) {
        this.progetti = progetti;
    }

    public Progetto createProgetto(String nome, String descrizione) {
        //TODO createProgetto
        Progetto progettoNeutro = new Progetto(nome, descrizione);
        this.progetti.add(progettoNeutro);
        return progettoNeutro;
    }

    public void requestEsperto(Progetto progettoNeutro) {
        //TODO requestEsperto
    }

    public void insertInfoProgettisti(Progetto progetto, Map<Specializzazione, Integer> in) {
        //TODO insertInfoProgettisti
        progetto.setInfoProgettistiRichiesti(in);
    }

    public void getProgetto(String idProponente) {
        //TODO WTF getProgetto
    }

    public void pubblicaProgetto(Progetto progetto) {
        //TODO pubblicaProgetto
    }

    public void getListaProgetti(String idProponente){
        //TODO getListaProgetti
    }

    public void getListaProgetti(String idProponente, StatoProgetto stato){
        //TODO getListaProgetti
    }

    public void selezionaCandidatura(String idProgetto, StatoProgetto stato){
        //TODO selezionaCandidatura
    }

    public void modificaStatoCandidatura(StatoProgetto stato){
        //TODO modificaStatoCandidatura
    }

    public void getInfoProgettisti(List<Progettista> progettisti){
        //TODO getInfoProgettisti
    }

    public void notificaEsito(String idProgettista){
        //TODO notificaEsito
    }
}
