package it.unicam.cs.ids.doit.utenti;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.lang.NonNull;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Esperto extends Utente {
    @NonNull
    private String nome;
    @NonNull
    private String cognome;
    @ElementCollection
    private Set<Specializzazione> specializzazioni = new HashSet<>();


    public Esperto() {
    }


    public Esperto(String username, String mailAddress, String nome, String cognome) {
        super(username, mailAddress);
        this.nome = nome;
        this.cognome = cognome;
    }

    public Esperto(String username, String mailAddress, String nome, String cognome, Set<Specializzazione> specializzazioni) {
        this(username, mailAddress, nome, cognome);
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

