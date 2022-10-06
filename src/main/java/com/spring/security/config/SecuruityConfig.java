package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(-101)
public class SecuruityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().

        antMatchers("/h2-console/**").permitAll().
				antMatchers("/save-emp").permitAll()
				.
		antMatchers("/").permitAll().antMatchers("/home").permitAll().antMatchers("/contact")
				.permitAll().antMatchers("/notices").permitAll().antMatchers("/create-user").permitAll()
				.antMatchers("/cards").authenticated().antMatchers("/account").authenticated()
		.anyRequest().authenticated()
		  .and().formLogin().and() .httpBasic()
		.and().
		csrf().disable();
		http.headers().frameOptions().disable();
		
		 

	}

	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new SCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
		
	}

}
