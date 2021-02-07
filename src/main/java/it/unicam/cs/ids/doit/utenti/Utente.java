package it.unicam.cs.ids.doit.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Utente {

    @Id
    @Column(name="member_id")
    private String id;
    @NonNull
    private String mailAddress;

    public Utente() {
    }

    public Utente(String id, String mailAddress) {
        this.id = id;
        this.mailAddress = mailAddress;
    }
    public Long getId()
    {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
