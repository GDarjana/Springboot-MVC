package unc.ueJava.TPFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TpFinalApplication {

	public static void main(String[] args) {
		// Chargement du context applicatif avec la classe de configuration
		System.out.println("Initialisation du contexte applicatif");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		SpringApplication.run(TpFinalApplication.class, args);
	}

}
