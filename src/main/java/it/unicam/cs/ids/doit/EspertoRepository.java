package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.utenti.Esperto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspertoRepository extends JpaRepository<Esperto, Long> {

}
