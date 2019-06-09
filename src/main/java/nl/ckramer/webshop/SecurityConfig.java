package nl.ckramer.webshop;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.and()
//			.httpBasic();
//	}
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().
//			withUser("user").password("password").roles("USER")
//			.and()
//			.withUser("admin").password("password").roles("USER", "ADMIN");;
//	}
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	    auth.jdbcAuthentication().dataSource(dataSource)
	        .usersByUsernameQuery("select *"
	            + " from auth_users where USER_ID=?")
	        .authoritiesByUsernameQuery("select *"
	            + " from auth_roles where USER_ID=?")
	        .passwordEncoder(new BCryptPasswordEncoder());
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {

		  http.authorizeRequests().antMatchers("/").permitAll();
	        http
	        .authorizeRequests()
	        .antMatchers("/shopping_cart.xhtml","/shopping_cart.xhtml/**").fullyAuthenticated()
	        .and()
	        .formLogin()
	        .loginPage("/login.xhtml")
	        .permitAll();
	  }
}