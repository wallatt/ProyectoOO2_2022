package com.unla.grupo13.TrabajoPractico.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.grupo13.TrabajoPractico.services.imp.UserServiceDetails;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userServiceDetails")
	private UserServiceDetails userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/*", "/imgs/*", "/js/*", "/vendor/bootstrap/css/*", "/vendor/jquery/*",
						"/vendor/bootstrap/js/*","/user/registro*","/user/create*")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/loginprocess").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/loginsucces").permitAll().and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/logout").permitAll();
	}
}
