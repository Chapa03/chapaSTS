package org.chapa.papJava.controller;

import java.util.List;

import org.chapa.papJava.entities.Aficion;
import org.chapa.papJava.exception.DangerException;
import org.chapa.papJava.exception.InfoException;
import org.chapa.papJava.exception.PRG;
import org.chapa.papJava.repository.AficionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AficionController {
	
	@Autowired
	private AficionRepository aficionRepository;

	@GetMapping("/aficion/r")
	public String r(ModelMap m) {
		List<Aficion> aficiones = aficionRepository.findAll();
		
		m.put("aficiones", aficiones);
		m.put("view", "aficion/r");
		return "_t/frame";
	}
	
	
	@GetMapping("/aficion/c")
	public String c(ModelMap m) {
		m.put("view", "aficion/c");
		return "_t/frame";
	}
	
	
	@PostMapping("/aficion/c")
	public void cPost(@RequestParam("descripcion") String descripcion) throws DangerException, InfoException { 
		try {
			aficionRepository.save(new Aficion(descripcion));
		} catch (Exception e) {
			PRG.error("La afición \"" + descripcion + "\" ya existe.", "/aficion/r");
		}
		PRG.info("\"" + descripcion + "\" se ha añadido correctamente.", "/aficion/r");
		//return "redirect://aficion/r";
	}
	
	@GetMapping("/aficion/u")
	public String u(ModelMap m) {
		m.put("view", "aficion/u");
		return "_t/frame";
	}
	
	
	@PostMapping("/aficion/u")
	public void uPost(@RequestParam("descripcion") String descripcion) throws DangerException, InfoException { 
		try {
			aficionRepository.save(new Aficion(descripcion));
		} catch (Exception e) {
			PRG.error("La afición \"" + descripcion + "\" ya existe.", "/aficion/r");
		}
		PRG.info("\"" + descripcion + "\" se ha añadido correctamente.", "/aficion/r");
		//return "redirect://aficion/r";
	}
}
