package it.unicam.cs.ids.doit.candidatura;

import java.util.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Candidatura {
//	@GenericGenerator(name = "sequence_cand_id", strategy = "it.unicam.cs.ids.doit.candidatura.CandidaturaIdGenerator")
//	@GeneratedValue(generator = "sequence_cand_id")
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private Long idProgetto;
	@NonNull
	private String idProgettista;
	@Enumerated(EnumType.STRING)
	private StatoCandidatura stato;
	@ElementCollection
    private Map<String, Boolean> pareriEsperti = new HashMap<String, Boolean>(); // idesperto, valutazione pos o neg

	public Candidatura() {

	}

	public Candidatura(Long idProgetto, String idProgettista) {
		this.idProgetto = idProgetto;
		this.idProgettista = idProgettista;
		this.stato = StatoCandidatura.DA_VALUTARE;
    }

    public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdProgetto() {
		return this.idProgetto;
	}
	
	public void setIdProgetto(Long idProgetto) {
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

