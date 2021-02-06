package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgettistaRepository extends JpaRepository<Progettista, Long> {

    Progettista findProgettistaByNome(String nome);

    Progettista findProgettistaByUsername(String username);

}
