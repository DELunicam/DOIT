package it.unicam.cs.ids.utenti;

public class Proponente extends Utente {
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
