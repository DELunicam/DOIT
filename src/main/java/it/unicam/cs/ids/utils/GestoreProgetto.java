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

    public Progetto createProgetto(String idProponente, String nome, String descrizione) {
        //TODO createProgetto
        Progetto progettoNeutro = new Progetto(idProponente, nome, descrizione);
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

    /**
     *
     * @param idProgetto
     * @return Progetto con id uguale a idProgetto, null se non esiste
     */
    public Progetto getProgetto(String idProgetto) {
        //TODO WTF getProgetto
        for(Progetto progetto : progetti){
            if(progetto.getId().equals(idProgetto)){
                return progetto;
            }
        }
        return null;
    }

    public void pubblicaProgetto(Progetto progetto) {
        //TODO pubblicaProgetto
    }

    public void getListaProgetti(String idProponente){
        //TODO getListaProgetti
    }

    public Set<Progetto> getListaProgetti(String idProponente, StatoProgetto stato){
        //TODO getListaProgetti
        Set<Progetto> result = new HashSet<>();
        for(Progetto progetto : progetti){
            if(progetto.getIdProponente().equals(idProponente) && progetto.getStatoProgetto().equals(stato)) {
                result.add(progetto);
            }
        }
        return result;
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
