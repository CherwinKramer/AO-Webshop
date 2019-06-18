package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.CategoryDao;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "categoryBean")
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryDao categoryDao;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	private List<Category> categories;
	
	private Category category = new Category();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		categories = categoryDao.findAll();
	}
	
	public void save() {
		categoryDao.save(category);
		setCategories(categoryDao.findAll());
		sessionBean.reinitializeCategories();
	}
	
	public void remove(Category category) {
		categoryDao.delete(category);
		setCategories(categoryDao.findAll());
		sessionBean.reinitializeCategories();
	}
	
	public void add(ActionEvent event) {
		category = new Category();
	}

}
