package it.unicam.cs.ids.doit.utenti;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String password;
    @NonNull
    private String username;
    @NonNull
    private String mailAddress;

    public Utente() {
    }

    public Utente(String username, String mailAddress) {
        this.username = username;
        this.mailAddress = mailAddress;
    }
    public Utente(String username, String password, String mailAddress) {
        this.username = username;
        this.password = password;
        this.mailAddress = mailAddress;
    }
    public Long getId()
    {
        return id;
    }

    public String getpassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
