package it.unicam.cs.ids.valutazione;

import java.util.*;

import it.unicam.cs.ids.progetto.Specializzazione;

public class Valutazione {
    private String id; // --> aggiunto, suppongo serva -luca
	private String idEsperto;
    private String idProgetto;
    private Boolean fattibilità; // false non fattibile, true fattibile
    private Map<Specializzazione, Integer> lavoratoriConsigliati; // --> aggiunto, suppongo serva -luca
    private String parere;

    public Valutazione() {

    }

    public Valutazione(String idProgetto, String idEsperto) {
        this.idProgetto = idProgetto;
        this.idEsperto = idEsperto;
        this.fattibilità = false;
    }

    public Valutazione(String idProgetto, String idEsperto, Map<Specializzazione, Integer> lavoratoriConsigliati) {
        this.idProgetto = idProgetto;
        this.idEsperto = idEsperto;
        this.fattibilità = true;
        this.lavoratoriConsigliati = lavoratoriConsigliati;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEsperto() {
        return this.idEsperto;
    }

    public void setIdEsperto(String idEsperto) {
        this.idEsperto = idEsperto;
    }

    public String getIdProgetto() {
        return this.idProgetto;
    }

    public void setIdProgetto(String idProgetto) {
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
