package it.unicam.cs.ids.doit.utenti;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import org.springframework.lang.NonNull;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Lavoratore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private String cognome;
    @ElementCollection
    private Set<Specializzazione> specializzazioni = new HashSet<>();
    @NonNull
    private Long idEnte;
    @ElementCollection
    private Set<Long> idProgettiSvolti = new HashSet<>();

    public Lavoratore() {
    }

    public Lavoratore(String nome, String cognome, Long idEnte) {
        this.nome = nome;
        this.cognome = cognome;
        this.idEnte = idEnte;
    }

    public Lavoratore(String nome, String cognome, Set<Specializzazione> specializzazioni, Long idEnte) { 
        this.nome = nome;
        this.cognome = cognome;
        this.specializzazioni = specializzazioni;
        this.idEnte = idEnte;
    }

    public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public Long getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}

    public void setIdProgettiSvolti(Set<Long> idProgettiSvolti) {
		this.idProgettiSvolti = idProgettiSvolti;
	}

	public Set<Long> getIdProgettiSvolti() {
		return this.idProgettiSvolti;
	}
    
    public String getInfoSpec() {
        String info = "Specializzazioni : \n";
        for (Specializzazione spec : specializzazioni) {
            info += spec.name() + " ";
        }
        return info;
    }

}

