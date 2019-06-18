package nl.ckramer.webshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.entity.AuthRole;
import nl.ckramer.webshop.entity.AuthUser;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserDao authUserDao;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}
 
		AuthUser user = authUserDao.findByUsername(username);
 
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedRole(user));
	}
 
	private List<GrantedAuthority> getGrantedRole(AuthUser user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		AuthRole role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}
	
}
