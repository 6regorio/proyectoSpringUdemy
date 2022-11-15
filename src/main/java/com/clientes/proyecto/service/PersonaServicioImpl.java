package com.clientes.proyecto.service;

import com.clientes.proyecto.dao.PersonaDao;
import com.clientes.proyecto.domain.Persona;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementa los métodos del servicio de persona.

 * @author gregorio.martinez
 *
 */
@Service
@Slf4j
public class PersonaServicioImpl implements PersonaServicio {

  @Autowired
  private PersonaDao personaDao;

  @Override
  @Transactional(readOnly = true)
  public List<Persona> listarPersonas() {

    log.info("Estoy en el servicio de persona");
    var listadoPersonas = (List<Persona>) personaDao.findAll();
    listadoPersonas.sort((p1, p2) -> p1.getIdPersona().compareTo(p2.getIdPersona()));
    return listadoPersonas;
  }

  @Override
  @Transactional
  public void guardar(Persona persona) {

    personaDao.save(persona);
  }

  @Override
  @Transactional
  public void eliminar(Persona persona) {
    personaDao.delete(persona);
  }

  @Override
  @Transactional(readOnly = true)
  public Persona encontrarPersona(Persona persona) {

    return personaDao.findById(persona.getIdPersona()).orElse(null);
  }

}
