package com.senai.ecommerce.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //arquivo de configuração
public class SecurityConfiguration {
	
	@Autowired
	private SecurityFilter securityFilter; //injeção de dependência do filtro de autenticação
	
	@Autowired
	private TokenService tokenService; //injeção de dependência do serviço de token
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	
//	  @Bean //permite a utlilização de todas as rotas
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//	    	http.csrf(csrf -> csrf.disable());
//	    	http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//	    	return http.build();
//	    }
	
	  @Bean //permite a utlilização de todas as rotas
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		  return http
			//permite o acesso a rota de login
				//desabilita o uso de sessão, pois a autenticação é feita via token JWT
					//desabilita o uso de sessão, pois a autenticação é feita via token JWT
			  	//e não é necessário manter uma sessão no servidor
		  	.csrf(csrf -> csrf.disable())
	
		  	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  	.authorizeHttpRequests(
		  			auth -> 
		  				auth
		  					.requestMatchers(HttpMethod.POST, "/usuario/login").permitAll()
		  					.requestMatchers(HttpMethod.POST, "/usuario/salvar").permitAll()
		  					.requestMatchers(HttpMethod.POST, "/produto/criar").hasRole("ADMIN")
		  					.requestMatchers(HttpMethod.DELETE, "/produto/{id}").hasRole("ADMIN")
		  					.anyRequest().authenticated())
		  	.addFilterBefore(securityFilter,UsernamePasswordAuthenticationFilter.class) //adiciona o filtro de autenticação
		  	.build(); //permite o acesso a rota de login
		  	
		  	
		  	
	  }
	  

	
	
}
