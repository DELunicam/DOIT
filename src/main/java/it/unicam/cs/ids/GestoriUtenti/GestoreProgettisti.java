package it.unicam.cs.ids.GestoriUtenti;

import it.unicam.cs.ids.progetto.Specializzazione;
import it.unicam.cs.ids.utils.FakeDb;
import it.unicam.cs.ids.utenti.Progettista;
import java.util.*;
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

        public String getInfoProgettista(String idProgettista)
        {   
            
            for (Progettista progettista : db.progettisti)
            {
                if(progettista.getId().equals(idProgettista))
                {
                    return progettista.getInfo();
                }
               
            }
            return null;
        }
}
