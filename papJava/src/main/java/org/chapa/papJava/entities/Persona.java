package org.chapa.papJava.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nombre;
	private String password;
	
	//Muchas personas pueden nacer en un país
	@ManyToOne
	private Pais nace;
	
	@ManyToOne
	private Pais reside;
	
	@ManyToMany
	private Collection<Aficion> aficionesGusta;
	
	@ManyToMany
	private Collection<Aficion> aficionesOdia;

	public Persona() {
		this.nombre = "Juanito";
		this.aficionesGusta = new ArrayList<Aficion>();
		this.aficionesOdia = new ArrayList<Aficion>();
	}
	
	public Persona (String nombre, String password, Pais nace, Pais reside) {
		this.nombre = nombre;
		this.password = encriptar(password);
		//Una persona creada ya tiene que pertenecer a un pais y resideir en un país
		this.nace = nace;
		this.reside = reside;
		//Recibe las colecciones de residentes y nativos y se añade en cada una (también en los setters)
		this.nace.getNativos().add(this);
		this.nace.getResidentes().add(this);
		
		this.aficionesGusta = new ArrayList<Aficion>();
		this.aficionesOdia = new ArrayList<Aficion>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}
			
	public void setPassword(String password) {
		this.password = encriptar(password);
	}
	
	
	public Pais getNace() {
		return nace;
	}

	public void setNace(Pais nace) {
		this.nace = nace;
		this.nace.getNativos().add(this);
	}
	

	public Collection<Aficion> getAficionesGusta() {
		return aficionesGusta;
	}

	public void setAficionesGusta(Collection<Aficion> aficionesGusta) {
		this.aficionesGusta = aficionesGusta;
	}
	
	public Pais getReside() {
		return reside;
	}

	public void setReside(Pais reside) {
		this.reside = reside;
		this.reside.getResidentes().add(this);
	}
	
	public Collection<Aficion> getAficionesOdia() {
		return aficionesOdia;
	}

	public void setAficionesOdia(Collection<Aficion> aficionesOdia) {
		this.aficionesOdia = aficionesOdia;
	}
	
	// ===============================


	//Método que codifica las contraseñas de los usuarios
	public String encriptar(String password) {
		return (new BCryptPasswordEncoder()).encode(password);
	}
	
	public void addAficionGusta(Aficion aficion) {
		this.aficionesGusta.add(aficion);
		aficion.getPersonasGustan().add(this);
	}
	
	public void addAficionOdia(Aficion aficion) {
		this.aficionesOdia.add(aficion);
		aficion.getPersonasOdian().add(this);
	}
	
}
