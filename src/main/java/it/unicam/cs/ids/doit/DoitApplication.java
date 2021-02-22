package it.unicam.cs.ids.doit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoitApplication.class, args);

//		DOIT.runApp(); // rimuovere dopo merge e usare startApp()
		DOIT.startApp(); // parte direttamente IVisitatore e si logga da lì
	}

}
