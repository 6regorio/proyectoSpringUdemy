package com.clientes.proyecto.controller;

import com.clientes.proyecto.domain.Persona;
import com.clientes.proyecto.service.PersonaServicio;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Clase del controlador principal.

 * @author gregorio.martinez
 *
 */
@Controller
@Slf4j
public class ControladorInicio {

  @Autowired
  private PersonaServicio personaServicio;

  /**
   * Entrada principal de la web.

   * @param model modelo
   * @param user usuario
   * @return string
   */
  @GetMapping("/")
  public String inicio(Model model, @AuthenticationPrincipal User user) {

    log.info("Estamos en la principal de ControladorInicio");
    log.info("usuario que hizo login:" + user);
    var personas = personaServicio.listarPersonas();

    // Aquí le mando el listado de personas
    model.addAttribute("personas", personas);

    return "index";
  }

  /**
   * Agrega o modifica una persona.

   * @param persona que se modificará
   * @return string
   */
  @GetMapping("/agregar")
  public String guardarModificarPersona(Persona persona) {

    log.info("Entrando en guardar de ControladorInicio");
    return "modificar";
  }

  /**
   * Guarda una persona dada.

   * @param persona a guardar
   * @param errors resultado de la validación
   * @return string
   */
  @PostMapping("/guardar")
  public String guardar(@Valid Persona persona, Errors errors) {
    if (errors.hasErrors()) {
      log.info("Existen errores y no se guarda");
      return "modificar";
    }
    personaServicio.guardar(persona);
    log.info("Se ha guardado la persona " + persona.getIdPersona());
    return "redirect:/";
  }

  /**
   * Edita una persona dada.

   * @param persona que se va a editar
   * @param model modelo
   * @return string
   */
  @GetMapping("/editar/{idPersona}")
  public String editar(Persona persona, Model model) {
    log.info("Entrando en editar de ControladorInicio");
    log.info("Se va a editar la persona " + persona.getIdPersona());
    persona = personaServicio.encontrarPersona(persona);
    model.addAttribute("persona", persona);
    return "modificar";
  }

  /**
   * Elimina una persona dada.

   * @param persona a eliminar
   * @return redirect
   */
  @GetMapping("/eliminar")
  public String eliminar(Persona persona) {
    log.info("Entrando en eliminar de ControladorInicio");
    log.info("Se va a eliminar la persona " + persona.getIdPersona());
    personaServicio.eliminar(persona);
    return "redirect:/";
  }

  /**
   * Acceso al login.

   * @return string
   */
  @GetMapping("/login")
  public String paginaLogin() {
    return "login";
  }
}