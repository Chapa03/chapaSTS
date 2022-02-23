package org.chapa.papJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = { SecurityAutoConfiguration.class }     Evita ceder el controlador a la dependencia de seguridad
//Que es capaz de crear casos de uso login, etc, para que podamos crear los propios y manejarlos nosotros
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PapJavaApplication {

	public static void main(String[] args) {
		//MAIN que deja la ejecución del aplicación en manos de Spring
		SpringApplication.run(PapJavaApplication.class, args);
	}

}
