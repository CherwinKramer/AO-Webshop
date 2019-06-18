package nl.ckramer.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService customUserDetailsService;
	
	@Autowired
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll();
		http.authorizeRequests().antMatchers("/index.xhtml").permitAll();
		http.authorizeRequests().antMatchers("/category.xhtml").permitAll();
		http.authorizeRequests().antMatchers("/register.xhtml").permitAll();
		http.authorizeRequests().antMatchers("/pages/management/**").hasAuthority("ADMIN").
		and().exceptionHandling().accessDeniedPage("/error/403.xhtml");
		
		http.authorizeRequests().anyRequest().authenticated();

		http.formLogin().loginPage("/login.xhtml").permitAll().failureUrl("/login.xhtml?error=true")
				.defaultSuccessUrl("/index.xhtml").usernameParameter("username").passwordParameter("password");

		http.logout().logoutUrl("/logout.xhtml").logoutSuccessUrl("/index.xhtml").and().exceptionHandling()
				.accessDeniedPage("/error/403.xhtml");

		http.csrf().disable().cors().disable();
	}

//	  @Autowired
//	  public void configureGlobal(AuthenticationManagerBuilder auth)
//	      throws Exception {
//	    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//	    .and().withUser("admin").password("password").roles("ADMIN");
//	  }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}