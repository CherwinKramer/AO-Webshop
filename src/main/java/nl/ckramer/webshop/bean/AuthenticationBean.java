package nl.ckramer.webshop.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.AuthRoleDao;
import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.entity.AuthRole;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.util.AutowireHelper;

@ManagedBean(name = "authenticationBean")
@Getter @Setter
@ViewScoped
public class AuthenticationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	AuthUserDao authUserDao;

	@Autowired
	AuthRoleDao authRoleDao;
	
	

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private AuthUser registerUser = new AuthUser();
	private String chosenRole = null;
	
	private AuthUser authUser;
	
	@PostConstruct
	public void initialize() {
		AutowireHelper.autowire(this);
	}
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public void registerAccount() throws IOException {
		AuthRole authRole = new AuthRole();
		authRole.setRole(chosenRole);
		authRole.setUsername(registerUser.getUsername());
		authRole = authRoleDao.save(authRole);
		
		registerUser.setPassword(bCryptPasswordEncoder().encode(registerUser.getPassword()));
		registerUser.setRole(authRole);
		authUserDao.save(registerUser);
		
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
	}

}