package it.unicam.cs.ids.utenti;

public class Utente {
    private String id;
    private String mailAddress;

    public Utente() {
    }

    public Utente(String id, String mailAddress) {
        this.id = id;
        this.mailAddress = mailAddress;
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
