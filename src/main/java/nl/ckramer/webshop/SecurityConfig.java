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
	
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    // require all requests to be authenticated except for the resources
	    http.authorizeRequests().antMatchers("/javax.faces.resource/**")
	        .permitAll().anyRequest().authenticated();
	    // login
	    http.formLogin().loginPage("/login.xhtml").permitAll()
	        .failureUrl("/login.xhtml?error=true");
	    // logout
	    http.logout().logoutSuccessUrl("/login.xhtml");
	    // not needed as JSF 2.2 is implicitly protected against CSRF
	    http.csrf().disable();
	  }

	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth)
	      throws Exception {
	    auth.inMemoryAuthentication().withUser("john.doe").password("{noop}1234").roles("USER")
	    .and().withUser("jane.doe").password("{noop}5678").roles("ADMIN");
	  }
	
//	@Override
//	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//	    auth.jdbcAuthentication().dataSource(dataSource)
//	        .usersByUsernameQuery("select *"
//	            + " from AuthUser where USER_ID=?")
//	        .authoritiesByUsernameQuery("select *"
//	            + " from AuthRole where USER_ID=?")
//	        .passwordEncoder(new BCryptPasswordEncoder());
//	  }
//
//	  @Override
//	  protected void configure(HttpSecurity http) throws Exception {
//
//		  http.authorizeRequests().antMatchers("/").permitAll();
//	        http
//	        .authorizeRequests()
//	        .antMatchers("/shopping_cart.xhtml","/shopping_cart.xhtml/**").fullyAuthenticated()
//	        .and()
//	        .formLogin()
//	        .loginPage("/login.xhtml").defaultSuccessUrl("/index.html").failureUrl("/login.xhtml?error=true")
//	        .permitAll();
//	        http.logout().logoutSuccessUrl("/login.xhtml");
//	  }
}