package org.gc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{ //manière pour chercher les users
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{ //définir les stratégies de sécu
        http.formLogin().loginPage("/login"); //auth basic par formulaire
        http.authorizeRequests().antMatchers("/operations", "/consulterCompte").hasRole("USER"); //sécurise les ressources de l'appli
        http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
