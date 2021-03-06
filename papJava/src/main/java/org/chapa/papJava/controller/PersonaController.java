package org.chapa.papJava.controller;

import java.util.List;

import org.chapa.papJava.entities.Persona;
import org.chapa.papJava.exception.DangerException;
import org.chapa.papJava.exception.InfoException;
import org.chapa.papJava.exception.PRG;
import org.chapa.papJava.repository.AficionRepository;
import org.chapa.papJava.repository.PaisRepository;
import org.chapa.papJava.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonaController {
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@Autowired
	private AficionRepository aficionRepository;

	@GetMapping("/persona/r")
	public String r(
			ModelMap m			
			) {
		List<Persona> personas = personaRepository.findAll();
		
		m.put("personas", personas);
		m.put("view", "/persona/r");
		return "_t/frame";
	}
	
	
	@GetMapping("/persona/c")
	public String c(ModelMap m) {
		//Obtiene la lista de paises
		m.put("paises", paisRepository.findAll());
		m.put("aficiones", aficionRepository.findAll());
		m.put("view", "/persona/c");
		return "_t/frame";
	}
	
	
	@PostMapping("/persona/c")
	public void cPost(
			@RequestParam("nombre") String nombre,
			@RequestParam("password") String password,
			@RequestParam("idPaisNace") Long idPaisNace,
			@RequestParam("idPaisReside") Long idPaisReside,
			/*Required false permite obtener una lista de aficiones vacía, por si no elige nada*/
			@RequestParam(value="idsAficionGusta[]", required=false) List<Long> idsAficionGusta,
			@RequestParam(value="idsAficionOdia[]", required=false) List<Long> idsAficionOdia
			) throws DangerException, InfoException { 
		try {
			Persona persona = new Persona(
					nombre, 
					password, 
					paisRepository.getById(idPaisNace), 
					paisRepository.getById(idPaisReside)
					);
			
			if (idsAficionGusta != null) {
				for (Long idAficionGusta : idsAficionGusta) {
					persona.addAficionGusta(aficionRepository.getById(idAficionGusta));
				}
			}
			if (idsAficionOdia != null) {
				for (Long idAficionOdia : idsAficionOdia) {
					persona.addAficionOdia(aficionRepository.getById(idAficionOdia));
				}
			}
			
			personaRepository.save(persona);
		} catch (Exception e) {
			PRG.error("Error inesperado al crear la persona" + e.getMessage());
		}
		PRG.info(nombre + " ha sido registrado correctamente.", "/persona/r");
	}
	
	@GetMapping("/persona/u")
	public String u(
			ModelMap m,
			@RequestParam("idPersona") Long idPersona
			) {
		m.put("persona", personaRepository.getById(idPersona));
		//Obtiene la lista de paises
		m.put("paises", paisRepository.findAll());
		m.put("aficiones", aficionRepository.findAll());
		m.put("view", "/persona/u");
		return "_t/frame";
	}
	
	
	@PostMapping("/persona/u")
	public void uPost(
			@RequestParam("idPersona") Long idPersona,
			@RequestParam("nombre") String nombre,
			@RequestParam("password") String password,
			@RequestParam("idPaisNace") Long idPaisNace,
			@RequestParam("idPaisReside") Long idPaisReside,
			/*Required false permite obtener una lista de aficiones vacía, por si no elige nada*/
			@RequestParam(value="idsAficionGusta[]", required=false) List<Long> idsAficionGusta,
			@RequestParam(value="idsAficionOdia[]", required=false) List<Long> idsAficionOdia
			) throws DangerException, InfoException { 
		try {
			Persona persona = personaRepository.getById(idPersona);
			persona.setNombre(nombre);
			persona.setPassword(password);
			persona.setNace(paisRepository.getById(idPaisNace));
			persona.setReside(paisRepository.getById(idPaisReside));
			if (idsAficionGusta != null) {
				for (Long idAficionGusta : idsAficionGusta) {
					persona.addAficionGusta(aficionRepository.getById(idAficionGusta));
				}
			}
			if (idsAficionOdia != null) {
				for (Long idAficionOdia : idsAficionOdia) {
					persona.addAficionOdia(aficionRepository.getById(idAficionOdia));
				}
			}
			
			personaRepository.save(persona);
		} catch (Exception e) {
			PRG.error("Error inesperado al editar la persona" + e.getMessage());
		}
		PRG.info(nombre + " ha sido modificado correctamente.", "/persona/r");
	}
}
