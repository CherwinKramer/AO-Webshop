package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.entity.AuthRole;
import nl.ckramer.webshop.service.AuthRoleService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped 
@ManagedBean(name = "authRoleBean")
public class AuthRoleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthRoleService authRoleService;
	
	private List<AuthRole> roles;
	
	private AuthRole role = new AuthRole();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		roles = authRoleService.findAll();
	}
	
	public void save() {
		authRoleService.save(role);
		role = new AuthRole();
		setRoles(authRoleService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "role saved!", null));
	}
	
	public void remove(AuthRole role) {
		authRoleService.remove(role);
		setRoles(authRoleService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "role removed!", null));
	}
	
	public void clear() {
		role = new AuthRole();
	}

}
