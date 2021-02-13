package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DoitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoitApplication.class, args);
		
	}

//	@Bean
//	public CommandLineRunner insertProgetti(ProgettoRepository progettoRepository) {
//		return (args) -> {
//			progettoRepository.save(new Progetto("PROGETTO1", "nome1", "desc1"));
//			progettoRepository.save(new Progetto("PROGETTO2", "nome2", "desc2"));
//			progettoRepository.save(new Progetto("PROGETTO3", "nome3", "desc3"));
//		};
//	}
//	@Bean
//	public CommandLineRunner insertProgettisti(ProgettistaRepository progettistaRepository) {
//		Set<Specializzazione> qualcheSpecializzazione = new HashSet<>();
//		qualcheSpecializzazione.add(Specializzazione.INFORMATICA);
//		qualcheSpecializzazione.add(Specializzazione.CHIMICA);
//		qualcheSpecializzazione.add(Specializzazione.INGEGNERIA);
//		return (args) -> {
//			progettistaRepository.save(new Progettista("PROG1", "email@email.com", "nome1", "desc1", qualcheSpecializzazione));
//			progettistaRepository.save(new Progettista("PROG2", "email@email.com", "nome2", "desc2", qualcheSpecializzazione));
//			progettistaRepository.save(new Progettista("PROG3", "email@email.com", "nome3", "desc3", qualcheSpecializzazione));
//		};
//	}
}
