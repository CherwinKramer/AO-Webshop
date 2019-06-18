package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.CountryDao;
import nl.ckramer.webshop.entity.Country;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "countryBean")
public class CountryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CountryDao countryDao;
	
	private List<Country> countries;
	
	private Country country = new Country();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		countries = countryDao.findAll();
	}
	
	public void save() {
		countryDao.save(country);
		setCountries(countryDao.findAll());
	}
	
	public void remove(Country country) {
		countryDao.delete(country);
		setCountries(countryDao.findAll());
	}
	
	public void add(ActionEvent event) {
		country = new Country();
	}

}
