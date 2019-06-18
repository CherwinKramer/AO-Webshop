package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.dao.LocationDao;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.Location;
import nl.ckramer.webshop.service.SelectItemService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "accountBean")
public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthUserDao authUserDao;

	@Autowired
	private SelectItemService selectItemService;

	@Autowired
	private LocationDao locationDao;

	private AuthUser authUser;
	private Location location = new Location();
	private List<Location> locations;
	private List<SelectItem> countries;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	@PostConstruct
	public void initialize() {
		AutowireHelper.autowire(this);
		authUser = authUserDao.findByUsername(authentication.getName());
		locations = locationDao.findByAuthUser(authUser);
		countries = selectItemService.loadCountryList();
	}

	public void saveLocation() {
		if (location.getAuthUser() == null) {
			location.setAuthUser(authUser);
		} else {
			locations.remove(location);
		}
		Location loc = locationDao.save(location);
		locations.add(loc);
		sessionBean.initializeLocations();
	}

	public void removeLocation(Location location) {
		locations.remove(location);
		locationDao.delete(location);
	}

	public void add() {
		location = new Location();
	}

}
