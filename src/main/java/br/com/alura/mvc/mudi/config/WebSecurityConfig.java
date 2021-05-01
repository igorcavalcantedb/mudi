package br.com.alura.mvc.mudi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		//.antMatchers("/", "/home/**").permitAll()
		.antMatchers("/").permitAll()
			.anyRequest()
			.authenticated()
			.and()
				// .httpBasic()
				// .and()
				
				.formLogin().loginPage("/login").defaultSuccessUrl("/pedido/pedidos", true).permitAll().and()

				.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		UserDetails user = 
//				User.builder()
//				.username("maria")
//				.password(encoder.encode("maria"))
//				.roles("USER")
//				.build();
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder);
			//.withUser(user);
	}
}
