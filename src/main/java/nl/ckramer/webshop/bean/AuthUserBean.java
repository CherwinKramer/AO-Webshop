package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.entity.AuthRole;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.service.AuthRoleService;
import nl.ckramer.webshop.service.AuthUserService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "authUserBean")
public class AuthUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private AuthRoleService authRoleService;
	
	private List<AuthUser> users;
	private List<AuthRole> rolesSelectList;
	
	private AuthUser user = new AuthUser();
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	
	@PostConstruct
	public void initialize() {
		AutowireHelper.autowire(this);
		rolesSelectList = authRoleService.findAll();
		users = authUserService.findAll();
	}
	
	public void save() {
		user.setPassword(encoder.encode(user.getPassword()));
		authUserService.save(user);
		user = new AuthUser();
		users = authUserService.findAll();
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "user saved!", null));
	}
	
	public void remove(AuthUser user) {
		authUserService.remove(user);
		users = authUserService.findAll();
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "user removed!", null));
	}
	
	public void clear() {
		user = new AuthUser();
	}

}
