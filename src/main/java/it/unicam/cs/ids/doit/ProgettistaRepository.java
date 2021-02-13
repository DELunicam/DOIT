<<<<<<< Updated upstream:src/main/java/it/unicam/cs/ids/doit/ProgettistaRepository.java
package it.unicam.cs.ids.doit;

=======
package it.unicam.cs.ids.doit.gestori_utenti;
import java.util.Set;
>>>>>>> Stashed changes:src/main/java/it/unicam/cs/ids/doit/gestori_utenti/ProgettistaRepository.java
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import it.unicam.cs.ids.doit.progetto.Specializzazione;

@Repository
public interface ProgettistaRepository extends JpaRepository<Progettista, Long> {

    public Progettista findProgettistaByNome(String nome);

    public Progettista findProgettistaByUsername(Long username);

<<<<<<< Updated upstream:src/main/java/it/unicam/cs/ids/doit/ProgettistaRepository.java
    Progettista findProgettistaByUsername(String username);
=======
    public Set<Progettista> findProgettistiByIdProgetto (Long idProgetto);
>>>>>>> Stashed changes:src/main/java/it/unicam/cs/ids/doit/gestori_utenti/ProgettistaRepository.java
    
    

    

}
