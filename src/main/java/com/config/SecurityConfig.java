package com.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.authorizeRequest().anyRequest().authenticated().and().httpBasic();
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//		http.authorizeHttpRequests(null)

//		http.authorizeHttpRequests(auth -> auth
//				  .requestMatchers("/authentication/**").permitAll()
//				  .requestMatchers("/h2/**").permitAll()
//				  .anyRequest().authenticated());
		System.out.println("==>Config");

//        http.authorizeHttpRequests().requestMatchers("/api/public/**").permitAll().anyRequest().authenticated()
//                .and().formLogin(withDefaults());
		http.authorizeHttpRequests(auth -> {

			auth.requestMatchers("/api/public/**").permitAll().requestMatchers("/api/user/**").hasRole("USER").requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated();
		});
		

		http.csrf(csrf -> csrf.disable());
		http.formLogin(withDefaults());
		http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		//http.authorizeHttpRequests(auth->auth.requestMatchers("/api/users/**").hasRole("USER"));
		//http.authorizeHttpRequests(auth->auth.requestMatchers("/api/admin/**").hasRole("ADMIN"));
		return http.build();
	}

//	@Bean
//	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("1234").roles("ADMIN").build();
//
//		return new InMemoryUserDetailsManager(admin);
//	}

	@Autowired
	DataSource dataSource;

	@Bean
	JdbcUserDetailsManager jdbcUserDetailsManager() {

		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		// jdbcUserDetailsManager.createUser(user);
		return jdbcUserDetailsManager;
	}

}
