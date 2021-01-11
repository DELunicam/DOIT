package it.unicam.cs.ids.GestoriUtenti;

import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.utils.FakeDb;
import it.unicam.cs.ids.utenti.Progettista;
import java.util.*;
import it.unicam.cs.ids.candidatura.*;
public class GestoreProgettisti 
{
    public static GestoreProgettisti instance;
    private final FakeDb db = new FakeDb();
    
    public GestoreProgettisti() {
    }

    // Singleton
    public static GestoreProgettisti getInstance() {
        if (instance == null) {
            instance = new GestoreProgettisti();
        }
        return instance;
    }

       public Progettista getProgettista(String idProgettista) {
        for (Progettista progettista : db.progettisti) {
            if (progettista.getId().equals(idProgettista))
                return progettista;
        }
        return null;
    }
        public Set<Specializzazione> getSpecializzazioni(String idProgettista)
        
        {
            for (Progettista progettista : db.progettisti) {
                if (progettista.getId().equals(idProgettista))
                    return progettista.getSpecializzazioni();
            }
            return null;
        }
        public Set<Progettista> getListaProgettisti()
        {
            Set<Progettista> progettisti = new HashSet<Progettista>();
            for (Progettista progettista : db.progettisti) 
            {
                progettisti.add(progettista);
            }
            return progettisti;
        }
        public String getProgettiSvolti(String idProgettista)

        {
            String progettiSvolti = "PROGETTI SVOLTI: \n";
            for(Candidatura candidatura : db.candidature)
            {
                if(candidatura.getIdProgettista().equals(idProgettista) && candidatura.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA))
                {
                   progettiSvolti += "ID PROGETTO: "+candidatura.getIdProgetto()+ "\n"; 
                }
            }
            return progettiSvolti;
        }
        public String getInfoProgettista(String idProgettista)
        {   
            String info = "INFO PROGETTISTA: \n";
            for (Progettista progettista : db.progettisti) {
                     info += 
                    "ID: " + progettista.getId() + "\n" +
                    "Nome: " + progettista.getNome() + "\n" +
                    "Cognome: " + progettista.getCognome() + "\n" +
                    progettista.getInfoSpec() "\n" +
                    "Progetti svolti: " + getProgettiSvolti(progettista.getId()) + "\n" +
                    "Mail: " + progettista.getMailAddress();
            }
            return info;
        }

        public Set<Progettista> getProgettisti(Set<Candidatura> candidatura)
         {
                    Set<Progettista> progettisti = new HashSet<Progettista>();
                    for (Candidatura cand : candidatura) {
                       progettisti.add(getProgettista(cand.getIdProgettista()));
                    }
                   return progettisti;
         }

         public String getInfoProgettisti(Set<Progettista> progettisti) 
         {
             String info = "INFO PROGETTISTI:\n";
              for (Progettista progettista : progettisti)
               {
                  info += this.getInfoProgettista(progettista.getId()) + "\n";
               }
                return info;
         }
       }       
