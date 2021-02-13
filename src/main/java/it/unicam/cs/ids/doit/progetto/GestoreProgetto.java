package it.unicam.cs.ids.doit.progetto;

import it.unicam.cs.ids.doit.ProgettoRepository;
import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utils.FakeDb;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestoreProgetto {
    private static GestoreProgetto instance;
    private final FakeDb db = new FakeDb(); // fake db
    @Autowired
    ProgettoRepository progettoRepository;

    public GestoreProgetto() {
    }

    // Singleton
    public static GestoreProgetto getInstance() {
        if (instance == null) {
            instance = new GestoreProgetto();
        }
        return instance;
    }

    public List<Progetto> findAllProgetti(){
        return progettoRepository.findAll();
    }

    public Progetto findProgetto(Long id){
        return progettoRepository.findById(id).get();
    }

    public Progetto createProgetto(String idProponente, String nome, String descrizione) {
        Progetto progettoNeutro = new Progetto(idProponente, nome, descrizione);
        db.addProgetto(progettoNeutro);
        return progettoNeutro;
    }

    public void requestEsperto(Progetto progettoNeutro) {
        progettoNeutro.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
    }

    public void insertInfoProgettisti(Progetto progetto, Map<Specializzazione, Integer> in) {
        progetto.setInfoProgettistiRichiesti(in);
    }

    /**
     * @param idProgetto
     * @return Progetto con id uguale a idProgetto, null se non esiste
     */
    public Progetto getProgetto(Long idProgetto) {
        for (Progetto progetto : db.progetti) {
            if (progetto.getId().equals(idProgetto))
                return progetto;
        }
        return null;
    }

    public void pubblicaProgetto(Progetto progetto) {
        progetto.setStatoProgetto(StatoProgetto.PUBBLICO);
    }

    public Set<Progetto> getListaProgetti() {
        return db.progetti;
    }

    public Set<Progetto> getListaProgetti(String idProponente) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getIdProponente().equals(idProponente))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }
   

    public Set<Progetto> getListaProgetti(String idProponente, StatoProgetto stato) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getIdProponente().equals(idProponente) && progetto.getStatoProgetto().equals(stato))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }
<<<<<<< Updated upstream

=======
   
    // TODO check implementazione migliore
>>>>>>> Stashed changes
    // Restituisce tutti i progetti aventi stato che richiedono una delle specializzazioni passate

    public Set<Progetto> getListaProgetti(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Specializzazione specializzazione : specializzazioni) {
            progettiCercati.addAll(getListaProgetti(specializzazione, statoProgetto));
        }
        return progettiCercati;
    }

    // Restituisce tutti i progetti aventi stato che richiedono la specializzazione passata

    private Set<Progetto> getListaProgetti(Specializzazione specializzazione, StatoProgetto statoProgetto) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getStatoProgetto().equals(statoProgetto) &&
                    progetto.getInfoProgettistiRichiesti().containsKey(specializzazione)) {
                progettiCercati.add(progetto);
            }
        }
        return progettiCercati;
    }

    public Set<Progetto> getListaProgetti(StatoProgetto statoProgetto) {
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Progetto progetto : db.progetti) {
            if (progetto.getStatoProgetto().equals(statoProgetto))
                progettiCercati.add(progetto);
        }
        return progettiCercati;
    }

    public Set<Progetto> getListaProgettiSvolti(Progettista progettista) {
        Set<Progetto> progettiSvolti = new HashSet<>();
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgettista().equals(progettista.getUsername()) && candidatura.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA)) {
                progettiSvolti.add(this.getProgetto(candidatura.getIdProgetto()));
            }
        }
        return progettiSvolti;
    }
    public Set<Progetto> getProgettiByIdIn(Set<Long> id)
    {
        return getProgettiByIdIn(id);
    }

    public Set<Progetto> getListaProgettiSvolti(String idProgettista) {
        Set<Progetto> progettiSvolti = new HashSet<>();
        for (Candidatura candidatura : db.candidature) {
            if (candidatura.getIdProgettista().equals(idProgettista) && candidatura.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA)) {
                progettiSvolti.add(this.getProgetto(candidatura.getIdProgetto()));
            }
        }
        return progettiSvolti;
    }

    public void notificaEsito(String idProgettista) {
        //TODO notificaEsito
    }

    public String getInfoProgetto(Long idProgetto) {
        return this.getProgetto(idProgetto).getInfo();
    }

    public void modificaStatoProgetto(Progetto progetto, StatoProgetto statoProgetto){
        progetto.setStatoProgetto(statoProgetto);
    }

    public void modificaStatoProgetto(Long idProgetto, StatoProgetto statoProgetto){
        Progetto progetto = this.getProgetto(idProgetto);
        progetto.setStatoProgetto(statoProgetto);
    }

}
