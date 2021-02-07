package it.unicam.cs.ids.doit.utenti;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    @NonNull
    private String mailAddress;

    public Utente() {
    }

    public Utente(String username, String mailAddress) {
        this.username = username;
        this.mailAddress = mailAddress;
    }
    public Long getId()
    {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String id) {
        this.username = id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
