package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.net.http.HttpHeaders;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
//public class MySecurityConfig  extends WebSecurityConfigurerAdapter{
public class MySecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	//authentication
	public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Amir")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("Ahmad")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.cors(cors -> cors.disable())
				.headers(headers -> headers.disable())
				.authorizeHttpRequests()
				.requestMatchers( "/token").permitAll()
				.requestMatchers("/employee/addUser").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);


		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		DefaultSecurityFilterChain build = http.build();

		return build;
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("CONTENT-TYPE","AUTHORIZATION"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			// We don't need CSRF for this example
			.csrf().disable()
			.cors().disable()
			.authorizeRequests()
			// dont authenticate this particular request
			.antMatchers("/token").permitAll()
			.antMatchers("/employee/addUser").permitAll()
			.anyRequest().authenticated()
			.and()
			//make sure we use stateless session; session won't be used to store user's state.
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}*/

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService);
	}
	*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
//	@Bean
//	public AuthenticationManager getAuthenticationManager() throws Exception {
//		return super.authenticationManagerBean();
//	}


}
