package org.chapa.papJava.repository;

import org.chapa.papJava.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	//Método de búsqueda por atributo
	public Persona getByNombre(String nombre);
}
