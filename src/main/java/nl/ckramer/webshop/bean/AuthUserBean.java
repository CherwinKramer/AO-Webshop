package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.AuthRoleDao;
import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.entity.AuthRole;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "authUserBean")
public class AuthUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthUserDao authUserDao;
	
	@Autowired
	private AuthRoleDao authRoleDao;
	
	private List<AuthUser> users;
	private List<AuthRole> rolesSelectList;
	
	private AuthUser user = new AuthUser();
	private String chosenRole;
	PasswordEncoder encoder = new BCryptPasswordEncoder();

	
	@PostConstruct
	public void initialize() {
		AutowireHelper.autowire(this);
		rolesSelectList = authRoleDao.findAll();
		users = authUserDao.findAll();
	}
	
	public void saveUser() {
		user.setRole(generateAuthRole(user));
		user.setPassword(encoder.encode(user.getPassword()));
		AuthUser us = authUserDao.save(user);
		clear();
		
		users.add(us);
	}
	
	public void remove(AuthUser user) {
		authUserDao.delete(user);
		users.remove(user);
	}

	public void clear() {
		user = new AuthUser();
		chosenRole = null;
	}
	
	private AuthRole generateAuthRole(AuthUser user) {
		AuthRole authRole = new AuthRole();
		authRole.setRole(user.getUsername());
		authRole.setRole(chosenRole);
		return authRoleDao.save(authRole);
	}

}
