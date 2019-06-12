package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.AuthRoleDao;
import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.entity.AuthRole;
import nl.ckramer.webshop.entity.AuthUser;

@Named("authenticationBean")
@Getter @Setter
@ViewScoped
public class AuthenticationBean implements Serializable, AuthenticationManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	AuthUserDao authUserDao;

	@Autowired
	AuthRoleDao authRoleDao;

	AuthUser user;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private AuthUser registerUser = new AuthUser();
	private List<AuthRole> rolesSelectList;
	
	@PostConstruct
	public void initialize() {
		rolesSelectList = authRoleDao.findAll();
		user = authUserDao.findByUsername("cherwin");
	}
	
	public void registerAccount() {
		authUserDao.save(registerUser);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";
		AuthUser user = authUserDao.findByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("1000");
		}
		if (!user.getEnabled()) {
			throw new DisabledException("1001");
		}
		if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("1000");
		}
		List<AuthRole> authRoles = user.getRoles();
		
		return new UsernamePasswordAuthenticationToken(username, password,
				authRoles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList()));
	}

}