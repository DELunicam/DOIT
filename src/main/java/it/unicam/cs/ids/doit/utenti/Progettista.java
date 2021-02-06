package it.unicam.cs.ids.doit.utenti;

import it.unicam.cs.ids.doit.progetto.Specializzazione;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.lang.NonNull;
@Entity
public class Progettista extends Utente {
    @NonNull
    private String nome;
    @NonNull
    private String cognome;
    @ElementCollection
    private Set<Specializzazione> specializzazioni = new HashSet<>();

    public Progettista() {
    }

    public Progettista(String id, String mailAddress, String nome, String cognome) {
        super(id, mailAddress);
        this.nome = nome;
        this.cognome = cognome;
    }

    public Progettista(String id, String mailAddress, String nome, String cognome, Set<Specializzazione> specializzazioni) {
        this(id, mailAddress, nome, cognome);
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

	public void setSpecializzazioni(Set<Specializzazione> specializzazioni) {
		this.specializzazioni = specializzazioni;
	}

	public Set<Specializzazione> getSpecializzazioni() {
		return this.specializzazioni;
	}

	public void addSpecializzazione(Specializzazione spec) {
		this.specializzazioni.add(spec);
	}

    public String getInfoSpec() {
        String info = "Specializzazioni : \n";
        for (Specializzazione spec : specializzazioni) {
            info += spec.name() + " ";
        }
        return info;
    }

}
