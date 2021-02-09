package it.unicam.cs.ids.doit.gestori_utenti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.unicam.cs.ids.doit.utenti.Ente;

@Repository
public interface EnteRepository extends JpaRepository<Ente, Long> {
    
}