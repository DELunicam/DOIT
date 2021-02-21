package it.unicam.cs.ids.doit.gestori_utenti;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GestoreProgettisti {
    public static GestoreProgettisti instance;
    @Autowired
    ProgettistaRepository progettistaRepository;

    public GestoreProgettisti() {
    }

    // Singleton
    public static GestoreProgettisti getInstance() {
        if (instance == null) {
            instance = new GestoreProgettisti();
        }
        return instance;
    }
    public Set<Progettista>getListaProgettistiById(Set<Long> id)
    {
        return progettistaRepository.findProgettistiByIdIn(id);
    }

    public Progettista getProgettista(Long idProgettista) {
        return progettistaRepository.findById(idProgettista).get();
    }

    public Progettista getProgettistaByNome(String nome) {
        return progettistaRepository.findProgettistaByNome(nome);
    }

    public Progettista getProgettistaByUsername(Long username) {
        return progettistaRepository.findProgettistaByUsername(username);
    }

    public Long getIdProgettistaByUsername(Long username) {
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

    // Probabilmente andra' spostato in GestoreProgetti
//    public Set<Progetto> getProgettiSvolti(String idProgettista) {
//        Set<Progetto> progettiSvolti = new HashSet<>();
//        GestoreProgetto gestoreProgetto = GestoreProgetto.getInstance();
//        for (Candidatura candidatura : db.candidature) {
//            if (candidatura.getIdProgettista().equals(idProgettista) && candidatura.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA)) {
//                progettiSvolti.add(gestoreProgetto.getProgetto(candidatura.getIdProgetto()));
//            }
//        }
//        return progettiSvolti;
//    }


//    public String getInfoProgettista(String idProgettista) {
//        String info = "INFO PROGETTISTA: \n";
//        for (Progettista progettista : db.progettisti) {
//            if (progettista.getId().equals(idProgettista)) {
//                info +=
//                        "ID: " + progettista.getId() + "\n" +
//                                "Nome: " + progettista.getNome() + "\n" +
//                                "Cognome: " + progettista.getCognome() + "\n" +
//                                progettista.getInfoSpec() + "\n" +
//                                "Progetti svolti: " + getProgettiSvolti(progettista.getId()) + "\n" +
//                                "Mail: " + progettista.getMailAddress();
//            }
//
//        }
//        return info;
//    }

    // TODO NON USATO? ELIMINARE?
    public Set<Progettista> getListaProgettisti(Set<Candidatura> candidatura) {
        Set<Progettista> progettisti = new HashSet<Progettista>();
        for (Candidatura cand : candidatura) {
            progettisti.add(getProgettista(getIdProgettistaByUsername(cand.getIdProgettista())));
        }
        return progettisti;
    }

//    public String getInfoProgettisti(Set<Progettista> progettisti) {
//        String info = "INFO PROGETTISTI:\n";
//        for (Progettista progettista : progettisti) {
//            info += this.getInfoProgettista(progettista.getId()) + "\n";
//        }
//        return info;
//    }
}
