package it.unicam.cs.ids.Progetto;

public class Candidatura {
	
	String idProgetto;
	String idProgettista;
	StatoCandidatura stato;
	
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
	
}
