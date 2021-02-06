package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.progetto.Progetto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgettoRepository extends JpaRepository<Progetto, Long> {

}
