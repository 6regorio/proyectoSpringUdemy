package com.clientes.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Configuración de contraseñas en memoria.

 * @author gregorio.martinez
 *
 */
@Configuration
@EnableWebSecurity
public class UserDetailServiceConfig {

  // @Autowired
  // private UserDetailsService userDetailsService;

  /**
   * Contraseñas en memoria.

   * @param bcryptPasswordEncoder encriptador
   * @return manager
   */
  @Bean
  public UserDetailsService userDetailsService(BCryptPasswordEncoder bcryptPasswordEncoder) {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(
            User.withUsername("user").password(bcryptPasswordEncoder.encode("userPass"))
            .roles("USER").build());
    manager.createUser(
        User.withUsername("admin").password(bcryptPasswordEncoder.encode("adminPass"))
        .roles("ADMIN", "USER").build());
    return manager;
  }

  /**
   * El bean de encriptar.

   * @return encriptador
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //  @Autowired
  //  public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
  //    builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  //  }
}