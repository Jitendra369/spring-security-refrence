package com.springboot.security.configure;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.client.HttpServerErrorException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	private String cookiePath;

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
		.antMatchers("/signin").permitAll()
		.antMatchers("/public/**").hasRole("NORMAL")
		.antMatchers("/users/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/do_process")
		.defaultSuccessUrl("/users/");

		
		

		cookiePath = CookieCsrfTokenRepository.withHttpOnlyFalse().getCookiePath();
		System.out.println(cookiePath);

		// .csrf().disable()
		// .antMatchers(HttpMethod.GET,"/public/**").permitAll()
		// .antMatchers("/public/**").permitAll()
		// .antMatchers("/home","/login","/dashboard","/register").permitAll()


		//role : high level overview 
		// Authority : permission
		// Admin : read,write,delete
		// normal user : read
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("jittu")
		.password(this.passwordEncoder()
		.encode("android"))
		.roles("NORMAL");
		auth.inMemoryAuthentication().withUser("neetu").password(this.passwordEncoder().encode("kadu")).roles("ADMIN");
		// to match the password with userpassword
		// System.out.println(this.passwordEncoder().matches("android", userpass));
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	
}