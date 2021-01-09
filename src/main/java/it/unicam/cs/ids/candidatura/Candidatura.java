package it.unicam.cs.ids.candidatura;

import java.util.*;

public class Candidatura {
    
    private String id;
	private String idProgetto;
	private String idProgettista;
    private StatoCandidatura stato;
    private Set<String> idEsperti = new HashSet<String>();

	public Candidatura() {

	}

	public Candidatura(String idProgetto, String idProgettista) {
		this.idProgetto = idProgetto;
		this.idProgettista = idProgettista;
    }

    public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdProgetto() {
		return this.idProgetto;
	}
	
	public void setIdProgetto(String idProgetto) {
		this.idProgetto = idProgetto;
	}
	
	public String getIdProgettista() {
		return this.idProgettista;
	}
	
	public void setIdProgettista(String idProgettista) {
		this.idProgettista = idProgettista;
	}
	
	public StatoCandidatura getStatoCandidatura() {
		return this.stato;
	}
	
	public void setStatoCandidatura(StatoCandidatura stato) {
		this.stato = stato;
	}
	
	public String getInfoCandidatura() {
		String info = "ID progetto: " + this.getIdProgetto() + "\n"
		+ "ID progettista: " + this.getIdProgettista() + "\n"
		+ "Stato candidatura: " + this.getStatoCandidatura().toString() + "\n";
		return info;
	}
	
	public void printInfoCandidatura() {
		System.out.println(this.getInfoCandidatura());
    }
    
    // --> aggiunto -luca
    // aggiunge l'id di un esperto che ha approvato la candidatura
    public void addEsperto(String idEsperto) {
        this.idEsperti.add(idEsperto);
    }
	
}

