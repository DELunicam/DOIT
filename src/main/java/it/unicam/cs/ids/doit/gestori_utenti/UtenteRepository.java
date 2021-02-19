package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    boolean existsByUsername(String username);

    Utente findByUsernameAndPassword(String username, String password);

    String findDtypeByUsernameAndPassword(String username, String password);

	Utente findByUsername(String username);
    
}
