package com.clientes.proyecto.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Se usa para configurar el cambio de idioma.

 * @author gregorio.martinez
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Se usa para configurar el cambio de idioma.

   * @return slr
   */
  @Bean
  public LocaleResolver localeResolver() {
    var slr = new SessionLocaleResolver();
    slr.setDefaultLocale(new Locale("es"));
    return slr;
  }

  /**
   * Se usa para configurar el cambio de idioma.

   * @return lci
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    var lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  /**
   * Se usa para configurar el cambio de idioma.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registro) {
    registro.addInterceptor(localeChangeInterceptor());
  }
}
