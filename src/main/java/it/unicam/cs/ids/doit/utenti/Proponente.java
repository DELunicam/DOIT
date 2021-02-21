package it.unicam.cs.ids.doit.utenti;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;

@Entity
public class Proponente extends Utente {
    @NonNull
    private String nome;

    private String cognome;


    public Proponente() {
    }

    public Proponente(String username, String mailAddress, String nome) {
        super(username, mailAddress);
        this.nome = nome;
    }

    public Proponente(String username, String password, String mailAddress, String nome, String cognome) {
        super(username, password, mailAddress);
        this.nome = nome;
        this.cognome = cognome;
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

}
