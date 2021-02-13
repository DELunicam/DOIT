package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.utenti.Proponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProponenteRepository extends JpaRepository<Proponente, Long> {
}
