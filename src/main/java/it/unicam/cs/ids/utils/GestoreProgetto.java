package it.unicam.cs.ids.utils;

import it.unicam.cs.ids.progetto.StatoCandidatura;
import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.progetto.Candidatura;
import it.unicam.cs.ids.progetto.Progetto;
import it.unicam.cs.ids.progetto.StatoProgetto;
import it.unicam.cs.ids.utenti.Progettista;

import java.util.*;

public class GestoreProgetto {
    private Set<Progetto> progetti;
    private Set<Candidatura> candidature;
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

    public Set<Candidatura> selezionaCandidatura(String idProgetto, StatoCandidatura stato)
    {
        //TODO selezionaCandidatura
        Set<Candidatura>  candidatureCercate = new HashSet<Candidatura>();
        for(Candidatura candidatura : this.candidature)
        {
            if(candidatura.getIdProgetto().equals(idProgetto) && candidatura.getStatoCandidatura().equals(stato))
            {
                candidatureCercate.add(candidatura);
            }
        }
        return candidatureCercate;
    }

    public void modificaStatoCandidatura(StatoCandidatura stato)
    {
        for (Candidatura candidatura : candidature) 
        {
            candidatura.setStatoCandidatura(stato);
        } 
    }



    public void getInfoProgettisti(Set<Progettista> progettisti)
    {
        for(Progettista progettista : progettisti)
        {
            System.out.println(progettista.getInfo());
        }

    }

    public void notificaEsito(String idProgettista)
    {
        
        for(Candidatura candidatura : candidature)
        {
            if(candidatura.getIdProgettista().equals(idProgettista))
            {
                System.out.println("La tua candidatura è stata:"+ candidatura.getStatoCandidatura());
            }
        }
    }
}
