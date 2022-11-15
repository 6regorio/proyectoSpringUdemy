package com.clientes.proyecto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad.

 * @author gregorio.martinez
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

  @Value("${spring.security.debug:false}")
  boolean securityDebug;

  /**
   * Configuración de seguridad y roles.

   * @param http HttpSecurity
   * @return SecurityFilterChain
   * @throws Exception de seguridad
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/editar/**", "/agregar/**", "/eliminar")
        .hasRole("ADMIN")
      .antMatchers("/")
        .hasAnyRole("USER", "ADMIN")
      //        .antMatchers("/")
      //            .anonymous()
      .and()
        .formLogin()
        .loginPage("/login");

    //        http.csrf()
    //          .disable()
    //          .authorizeRequests()
    //          
    //          .antMatchers("/")
    //          .anonymous()
    //          
    //          .antMatchers("/agregar","/editar/**","/eliminar","/")
    //          .hasRole("ADMIN")
    //          .antMatchers("/admin/**")
    //          .hasAnyRole("ADMIN")
    //          .antMatchers("/user/**")
    //          .hasAnyRole("USER", "ADMIN")
    //          .antMatchers("/login/**")
    //          .anonymous()
    //          .anyRequest()
    //          .authenticated()
    //          .and()
    //          .httpBasic()
    //          .and()
    //          .sessionManagement()
    //          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }

  /**
   * Excepciones de seguridad.

   * @return excepciones
   */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.debug(securityDebug).ignoring()
          .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
  }
}
