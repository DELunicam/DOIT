package it.unicam.cs.ids.Progetto;

import java.util.HashMap;
import java.util.HashSet;

import it.unicam.cs.ids.Utenti.Progettista;

public class Progetto {
	
	String id;
	String nome;
	String descrizione;
	StatoProgetto stato;
	HashSet<Progettista> progettisti = new HashSet<Progettista>();
	HashMap<String, Integer> infoProgettistiRichiesti = new HashMap<String, Integer>(); // tag e numero necessario
	HashSet<Candidatura> candidature = new HashSet<Candidatura>();
	
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
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
		return this.stato;
	}
	
	public void setStatoProgetto(StatoProgetto statoProgetto) {
		this.stato = statoProgetto;
	}
	
	public void setProgettista(Progettista tizio) {
		this.progettisti.add(tizio);
	}
	
	public void setProgettisti(HashSet<Progettista> set) {
		for (Progettista tizio : set) {
			  setProgettista(tizio);
			}
	}
	// credo non serva, fatto con id tante volte servisse
	public Progettista getProgettista(String givenId) {
		Progettista cercato = new Progettista();
		for (Progettista tizio : this.progettisti) {
			  if (tizio.getId().equals(givenId))
				  cercato = tizio;
			}
		return cercato;
	}
	
	public HashSet<Progettista> getProgettisti() {
		return this.progettisti;
	}
	
	public String getStringProgettisti() {
		String out = new String();
		for (Progettista tizio : this.progettisti) {
			out += "ID: " + tizio.getId() + "\n";
			// System.out.println("ID: " + tizio.id + ", nome: " + tizio.nome);
			}
		return out;
	}
	
	public void setSingleInfoProgettistiRichiesti(String tag, Integer numero) {
		this.infoProgettistiRichiesti.put(tag, numero);
	}
	
	public void setMultipleInfoProgettistiProgettisti(HashMap<String, Integer> in) {
		for (String i :in.keySet()) {
			  setSingleInfoProgettistiRichiesti(i, in.get(i));
			}
	}
	
	public String getInfoProgettistiRichiesti() {
		String out = new String();
		for (String i : this.infoProgettistiRichiesti.keySet()) {
			out += "Specializzazione: " + i + ", numero progettisti necessari: " + this.infoProgettistiRichiesti.get(i) + "\n";
			}
		return out;
	}
	
	public Integer getNumeroProgettistiRichiesti() {
		Integer out = 0;
		for (String i : this.infoProgettistiRichiesti.keySet()) {
			out += this.infoProgettistiRichiesti.get(i);
			}
		return out;
	}
	
	// candidature???
	
	// extra per comodità
	public void setInfo(String id, String nome, String descrizione, StatoProgetto stato) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.stato = stato;
	}
	
	public String getInfo() {
		String info = "ID: " + this.getId() + "\n"
		+ "Nome: " + this.getNome() + "\n"
		+ "Descrizione: " + this.getDescrizione() + "\n"
		+ "Stato: " + this.getStatoProgetto() + "\n"
		+ "Progettisti: \n" + this.getProgettisti() + "\n"
		+ "Progettisti richiesti: \n" + this.getInfoProgettistiRichiesti() + "\n"
		;
		return info;
	}
	
	public void printInfo() {
		System.out.println(this.getInfo());
	}
	
}
