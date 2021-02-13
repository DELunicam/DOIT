package it.unicam.cs.ids.doit.associazione;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Associazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Long idEnte;
    @NonNull
    private Long idProgettista;
    @NonNull
    private Long idProgetto;
	@Enumerated(EnumType.STRING)
    private StatoAssociazione stato;

    public Associazione() {

    }

	public Associazione(Long idEnte, Long idProgettista, Long idProgetto) {
		this.idEnte = idEnte;
		this.idProgettista = idProgettista;
		this.idProgetto = idProgetto;
		this.stato = StatoAssociazione.PROPOSTA;
	}

    public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}

    public Long getIdProgettista() {
		return this.idProgettista;
	}

	public void setIdProgettista(Long idProgettista) {
		this.idProgettista = idProgettista;
	}

	public Long getIdProgetto() {
		return this.idProgetto;
	}

	public void setIdProgetto(Long idProgetto) {
		this.idProgetto = idProgetto;
	}

	public StatoAssociazione getStatoAssociazione() {
		return this.stato;
	}

	public void setStatoAssociazione(StatoAssociazione stato) {
		this.stato = stato;
	}
    
}
