package org.chapa.papJava.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String nombre;
	
	//En un país pueden nacer muchas personas (una colección de personas)
	@OneToMany(mappedBy = "nace")
	private Collection<Persona> nativos;
	
	@OneToMany(mappedBy = "reside")
	private Collection<Persona> residentes;
	
	public Pais() {
		this.nombre = "Atlantida";
		this.nativos = new ArrayList<Persona>();
		this.residentes = new ArrayList<Persona>();
	}
	
	public Pais (String nombre) {
		this.nombre = nombre;
		this.nativos = new ArrayList<Persona>();
		this.residentes = new ArrayList<Persona>();
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

	public Collection<Persona> getNativos() {
		return nativos;
	}

	public void setNativos(Collection<Persona> nativos) {
		this.nativos = nativos;
	}

	public Collection<Persona> getResidentes() {
		return residentes;
	}

	public void setResidentes(Collection<Persona> residentes) {
		this.residentes = residentes;
	}
	
	
	
}
