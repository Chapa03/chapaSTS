package org.chapa.papJava.repository;

import org.chapa.papJava.entities.Aficion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AficionRepository extends JpaRepository<Aficion, Long>{

}
