package it.unicam.cs.ids.doit.utenti;

import javax.persistence.Entity;

import org.springframework.lang.NonNull;

@Entity
public class Proponente extends Utente {
    @NonNull
    private String nome;
    
	public Proponente() {
	}

	public Proponente(String id, String mailAddress, String nome) {
		super(id, mailAddress);
		this.nome = nome;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
