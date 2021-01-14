package it.unicam.cs.ids.candidatura;

import java.util.*;

public class Candidatura {
    
    private String id;
	private String idProgetto;
	private String idProgettista;
    private StatoCandidatura stato;
    private Map<String, Boolean> pareriEsperti = new HashMap<String, Boolean>(); // idesperto, valutazione pos o neg

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

	public Map<String, Boolean> getPareriEsperti() {
		return this.pareriEsperti;
	}
	public void setPareriEsperti(Map<String, Boolean> parereEsperti) {
		this.pareriEsperti = parereEsperti;
	}
	
    public void addParereEsperto(String idEsperto, Boolean posNeg) {
        this.pareriEsperti.put(idEsperto, posNeg);
    }
	
	public String getInfoCandidatura() {
		String info = "ID: " + this.getId() + "\n"
		+ "ID progetto: " + this.getIdProgetto() + "\n"
		+ "ID progettista: " + this.getIdProgettista() + "\n"
		+ "Stato candidatura: " + this.getStatoCandidatura().toString() + "\n";
		return info;
	}
	
	public void printInfoCandidatura() {
		System.out.println(this.getInfoCandidatura());
    }
    
	
}

