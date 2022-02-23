package org.chapa.papJava.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Aficion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String descripcion;
	
	//El mapeo es para que se reconozcan una clase a la otra
	//en una relacion MTM. Se hace el mapeo en cualquiera de
	//las dos clases refiriéndose al atributo de la clase contraria
	@ManyToMany(mappedBy = "aficionesGusta")
	private Collection<Persona> personasGustan;
	
	@ManyToMany(mappedBy = "aficionesOdia")
	private Collection<Persona> personasOdian;
	
	public Aficion() {
		this.descripcion = "Castañetear las uñas";
		this.personasGustan = new ArrayList<Persona>();
		this.personasOdian = new ArrayList<Persona>();
	}
	
	public Aficion (String descripcion) {
		this.descripcion = descripcion;
		this.personasGustan = new ArrayList<Persona>();
		this.personasOdian = new ArrayList<Persona>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Persona> getPersonasGustan() {
		return personasGustan;
	}

	public void setPersonasGustan(Collection<Persona> personasGustan) {
		this.personasGustan = personasGustan;
	}

	public Collection<Persona> getPersonasOdian() {
		return personasOdian;
	}

	public void setPersonasOdian(Collection<Persona> personasOdian) {
		this.personasOdian = personasOdian;
	}
	
	
	
}
