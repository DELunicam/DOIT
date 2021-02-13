package it.unicam.cs.ids.doit.gestori_utenti;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Lavoratore;

@Repository
public interface LavoratoreRepository extends JpaRepository<Lavoratore, Long> {

    public Set<Lavoratore> findLavoratoriByIdEnte(Long idEnte);
    
	public Set<Lavoratore> findLavoratoriByIdEnteAndSpecializzazioniIn(Long idEnte, Set<Specializzazione> specializzazioni);
    
}