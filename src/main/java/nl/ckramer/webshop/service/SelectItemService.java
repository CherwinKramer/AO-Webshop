package nl.ckramer.webshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.CategoryDao;
import nl.ckramer.webshop.dao.CountryDao;
import nl.ckramer.webshop.dao.LocationDao;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Country;
import nl.ckramer.webshop.entity.Location;

@Service
public class SelectItemService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private LocationDao locationDao;

	public List<SelectItem> loadCategoryList() {
		List<SelectItem> list = new ArrayList<>();
		List<Category> categories = categoryDao.findAll();
		for (Category category : categories) {
			list.add(new SelectItem(category, category.getName()));
		}
		return list;
	}
	
	public List<SelectItem> loadCountryList() {
		List<SelectItem> list = new ArrayList<>();
		List<Country> countries = countryDao.findAll();
		for (Country country : countries) {
			list.add(new SelectItem(country, country.getName()));
		}
		return list;
	}
	
	public List<SelectItem> loadLocationList(AuthUser authUser) {
		List<SelectItem> list = new ArrayList<>();
		List<Location> locations = locationDao.findByAuthUser(authUser);
		for (Location location : locations) {
			list.add(new SelectItem(location, location.getName() + " ~ " + location.getAddressLine1()));
		}
		return list;
	}
}
