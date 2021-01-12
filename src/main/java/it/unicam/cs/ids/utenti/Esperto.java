package it.unicam.cs.ids.utenti;

import it.unicam.cs.ids.progetto.Specializzazione;

import java.util.HashSet;
import java.util.Set;


public class Esperto extends Utente {

    private String nome;
    private String cognome;
    private Set<Specializzazione> specializzazioni = new HashSet<>();


    public Esperto() {
    }

    public Esperto(String id, String mailAddress, String nome, String cognome) {
        super(id, mailAddress);
        this.nome = nome;
        this.cognome = cognome;
    }

    public Esperto(String id, String mailAddress, String nome, String cognome, Set<Specializzazione> specializzazioni) {
        this(id, mailAddress, nome, cognome);
        this.specializzazioni = specializzazioni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Set<Specializzazione> getSpecializzazioni() {
        return specializzazioni;
    }

    public void setSpecializzazioni(Set<Specializzazione> specializzazioni) {
        this.specializzazioni = specializzazioni;
    }

    public void addSpecializzazione(Specializzazione spec) {
        specializzazioni.add(spec);

    }
}

