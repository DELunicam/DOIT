package it.unicam.cs.ids.doit.valutazione;

import java.util.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

import it.unicam.cs.ids.doit.progetto.Specializzazione;

@Entity
public class Valutazione {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    @NonNull
    private Long idEsperto;
    @NonNull
    private Long idProgetto;
    @NonNull
    private Boolean fattibilità;
    @ElementCollection
    private Map<Specializzazione, Integer> lavoratoriConsigliati;
    private String parere;

    public Valutazione() {

    }

    public Valutazione(Long idProgetto, Long idEsperto) {
        this.idProgetto = idProgetto;
        this.idEsperto = idEsperto;
        this.fattibilità = false;
    }

    public Valutazione(Long idProgetto, Long idEsperto, Map<Specializzazione, Integer> lavoratoriConsigliati) {
        this.idProgetto = idProgetto;
        this.idEsperto = idEsperto;
        this.fattibilità = true;
        this.lavoratoriConsigliati = lavoratoriConsigliati;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEsperto() {
        return this.idEsperto;
    }

    public void setIdEsperto(Long idEsperto) {
        this.idEsperto = idEsperto;
    }

    public Long getIdProgetto() {
        return this.idProgetto;
    }

    public void setIdProgetto(Long idProgetto) {
        this.idProgetto = idProgetto;
    }

    public Boolean getFattibilità() {
        return this.fattibilità;
    }

    public void setFattibilità(Boolean fattibilità) {
        this.fattibilità = fattibilità;
    }

    public String getParere() {
        return this.parere;
    }

    public void setParere(String parere) {
        this.parere = parere;
    }

    public Map<Specializzazione, Integer> getLavoratoriConsigliati() {
        return this.lavoratoriConsigliati;
    }

    public void setLavoratoriConsigliati(Map<Specializzazione, Integer> lavoratoriConsigliati) {
        this.lavoratoriConsigliati = lavoratoriConsigliati;
    }

}
