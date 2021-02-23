package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ProgettistaRepository extends JpaRepository<Progettista, Long> {

    Progettista findProgettistaByNome(String nome);

    Progettista findProgettistaByUsername(Long username);

    Set<Progettista> findProgettistiByIdIn(Set<Long> id);

    Progettista findProgettistaByUsername(String username);
    
}
