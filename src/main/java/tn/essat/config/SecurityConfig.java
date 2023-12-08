package tn.essat.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tn.essat.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource datasource;
	@Autowired
	IUserService service;
	@Override //fel 6 twali bean wel extends tetnaha
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder crt=cryptageMP();
		auth.userDetailsService(service).passwordEncoder(crt);
		/*
		auth.jdbcAuthentication().dataSource(datasource)
								.usersByUsernameQuery("select username as principal, password as credentials, active from user where username=?")
								.authoritiesByUsernameQuery("select username as principal, role as role from user_roles where username=?")
								.passwordEncoder(crt)
								.rolePrefix("ROLE_");
		
		
		/*
		auth.inMemoryAuthentication().withUser("ali")
									 .password(crt.encode("essat"))
									 .roles("USER");
		auth.inMemoryAuthentication().withUser("aya")
									 .password(crt.encode("essat"))
									 .roles("ADMIN");
		*/
	}
	
	@Override //fel 6 twali bean
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.formLogin();
		http.formLogin().loginPage("/login")
						.defaultSuccessUrl("/")
						.failureUrl("/login?error=true").permitAll();
		http.authorizeRequests().antMatchers("/add**", "/delete**/**","/save").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		
		//http.csrf();
		http.csrf().disable(); 

	}
	
	@Bean
	public PasswordEncoder cryptageMP() {
		return new BCryptPasswordEncoder();
	}
}
