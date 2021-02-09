package it.unicam.cs.ids.doit.utenti;

import javax.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
public class Ente extends Utente {

    @NonNull
    private String nome;
    private String tipologia;
    private String descrizione;

    public Ente() {

    }

    public Ente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String toString() {
        return "ID: " + this.getId() + "\n"
            + "Nome: " + this.getNome() + "\n"
            + "Tipologia: " + this.getTipologia() + "\n"
            + "Descrizione: " + this.getDescrizione() + "\n"
            + "E-Mail: " + this.getMailAddress() + "\n"
            + "Username: " + this.getUsername();
    }
    
}
