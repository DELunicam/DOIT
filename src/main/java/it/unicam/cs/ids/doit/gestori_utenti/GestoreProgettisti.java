package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GestoreProgettisti {

    @Autowired
    ProgettistaRepository progettistaRepository;

    private GestoreProgettisti() {
    }

    public Set<Progettista>getListaProgettistiById(Set<Long> id)
    {
        return progettistaRepository.findProgettistiByIdIn(id);
    }

    public Progettista getProgettista(Long idProgettista) {
        if (progettistaRepository.existsById(idProgettista)) {
            return progettistaRepository.findById(idProgettista).get();
        }
        return null;
    }

    public Progettista getProgettistaByNome(String nome) {
        return progettistaRepository.findProgettistaByNome(nome);
    }

    public Progettista getProgettistaByUsername(String username) {
        return progettistaRepository.findProgettistaByUsername(username);
    }

    public Long getIdProgettistaByUsername(String username) {
        return progettistaRepository.findProgettistaByUsername(username).getId();
    }

    public Set<Specializzazione> getSpecializzazioni(Long idProgettista) {
        return progettistaRepository.findById(idProgettista).get().getSpecializzazioni();
    }


    public Set<Progettista> getListaProgettisti() {
        Set<Progettista> progettisti = new HashSet<Progettista>();
        progettistaRepository.findAll().forEach(progettisti::add);
        return progettisti;
    }

	public Progettista creaProgettista(String username, String password, String mail, String nome, String cognome, Set<Specializzazione> specializzazioni) {
        Progettista progettista = new Progettista(username, BCrypt.hashpw(password, BCrypt.gensalt()), mail, nome, cognome, specializzazioni);
        progettistaRepository.save(progettista);
        return progettista;
	}

}
