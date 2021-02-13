package it.unicam.cs.ids.doit.utenti;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;

@Entity
public class Proponente extends Utente {
    @NonNull
    private String nome;

    public Proponente() {
    }

    public Proponente(String username, String mailAddress, String nome) {
        super(username, mailAddress);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
