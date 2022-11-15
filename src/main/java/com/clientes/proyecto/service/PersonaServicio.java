package com.clientes.proyecto.service;

import com.clientes.proyecto.domain.Persona;
import java.util.List;

/**
 * Interfaz con las cabeceras de los métodos del servicio de persona.

 * @author gregorio.martinez
 *
 */
public interface PersonaServicio {

  public List<Persona> listarPersonas();

  public void guardar(Persona persona);

  public void eliminar(Persona persona);

  public Persona encontrarPersona(Persona persona);

}