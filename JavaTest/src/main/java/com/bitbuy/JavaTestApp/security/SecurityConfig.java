package com.bitbuy.JavaTestApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
          .inMemoryAuthentication()
          .withUser("user")
          .password("user123")
          .roles("USER");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/api/users").hasRole("USER")
          .antMatchers("/api/register").permitAll()
          .antMatchers("/api/login").permitAll()
          .and().formLogin();
        
        http.csrf().disable();
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
    }
}
