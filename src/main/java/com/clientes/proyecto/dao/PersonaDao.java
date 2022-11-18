package com.clientes.proyecto.dao;

import com.clientes.proyecto.domain.Persona;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz DAO que extiende crudRepository para persona.

 * @author gregorio.martinez
 *
 */
public interface PersonaDao extends CrudRepository<Persona, Long> {

}