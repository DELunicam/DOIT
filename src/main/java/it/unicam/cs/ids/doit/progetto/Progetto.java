package it.unicam.cs.ids.doit.progetto;

import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Progetto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nome;
    private String descrizione;
	@Enumerated(EnumType.STRING)
    private StatoProgetto statoProgetto;
    @NonNull
    private Long idProponente;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Specializzazione, Integer> infoProgettistiRichiesti = new HashMap<>();

    public Progetto() {
    }

    public Progetto(Long idProponente, String nome, String descrizione) {
        this.idProponente = idProponente;
        this.nome = nome;
        this.descrizione = descrizione;
        this.statoProgetto = StatoProgetto.NEUTRO;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public StatoProgetto getStatoProgetto() {
        return this.statoProgetto;
    }

    public void setStatoProgetto(StatoProgetto statoProgetto) {
        this.statoProgetto = statoProgetto;
    }

    public Long getIdProponente() {
        return this.idProponente;
    }

    public void setIdProponente(Long idProponente) {
        this.idProponente = idProponente;
    }

    public Map<Specializzazione, Integer> getInfoProgettistiRichiesti() {
        return infoProgettistiRichiesti;
    }

    public void setInfoProgettistiRichiesti(Map<Specializzazione, Integer> infoProgettistiRichiesti) {
        this.infoProgettistiRichiesti = infoProgettistiRichiesti;
    }

    public Integer getNumeroProgettistiRichiesti() {
        Integer totale = 0;
        for (Specializzazione i : this.infoProgettistiRichiesti.keySet()) {
            totale += this.infoProgettistiRichiesti.get(i);
        }
        return totale;
    }

    public void addSingleInfoProgettistiRichiesti(Specializzazione tag, Integer numero) {
        this.infoProgettistiRichiesti.put(tag, numero);
    }

    public void addMultipleInfoProgettistiRichiesti(Map<Specializzazione, Integer> in) {
        this.infoProgettistiRichiesti.putAll(in);
    }

}
