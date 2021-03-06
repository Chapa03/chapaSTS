package org.chapa.papJava.controller;

import java.util.List;

import org.chapa.papJava.entities.Pais;
import org.chapa.papJava.exception.DangerException;
import org.chapa.papJava.exception.InfoException;
import org.chapa.papJava.exception.PRG;
import org.chapa.papJava.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaisController {
	@Autowired
	private PaisRepository paisRepository;		//Conexion con la interfaz que gestiona la BBDD
												//que necesitamos en cPost

	@GetMapping("/pais/r")
	public String r(
			ModelMap m					/* Es el empaquetado de paises que va a recibir la vista */
			) {
		/* Lista de paises que recibimos del repositorio de paises (interfaz) */
		List<Pais> paises = paisRepository.findAll();
		
		/* ModelMap con países que Spring pondrá a disposición de la
			vista que despliegue (pais/r en este caso) */
		m.put("paises", paises);
		m.put("view", "/pais/r");
		return "_t/frame";
	}
	
	
	@GetMapping("/pais/c")
	public String c(ModelMap m) {
		m.put("view", "/pais/c");
		return "_t/frame";
	}
	
	
	@PostMapping("/pais/c")
	public void cPost(@RequestParam("nombre") String nombre) throws DangerException, InfoException { /* El nombre de pais que se recibe del form de país/c */
		try {
			paisRepository.save(new Pais(nombre));	/* Guardado del país cuyo nombre llega por POST */
		} catch (Exception e) {
			PRG.error("El país " + nombre + " ya existe.", "/pais/c");
		}
		PRG.info(nombre + " creado correctamente.", "/pais/r");
	}
	
	@GetMapping("/pais/u")
	public String u(
			ModelMap m,
			@RequestParam("idPais") Long idPais
			) {
		m.put("pais", paisRepository.getById(idPais));
		m.put("view", "/pais/u");
		return "_t/frame";
	}
	
	
	@PostMapping("/pais/u")
	public void uPost(
			@RequestParam("idPais") Long idPais, /*Se recibe el id por un hidden*/
			@RequestParam("nombre") String nombre
			) throws DangerException, InfoException {
		try {
			Pais pais = paisRepository.getById(idPais); /*Se busca el país por el id*/
			pais.setNombre(nombre);
			paisRepository.save(pais);	/* Guardado del país cuyo nuevo nombre llega por POST */
		} catch (Exception e) {
			PRG.error("El país " + nombre + " ya existe.", "/pais/r");
		}
		PRG.info(nombre + " cambiado correctamente.", "/pais/r");
	}
}
