package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.service.CategoryService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "categoryBean")
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService categoryService;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	private List<Category> categories;
	
	private Category category = new Category();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		categories = categoryService.findAll();
	}
	
	public void save() {
		categoryService.save(category);
		clear();
		setCategories(categoryService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "category saved!", null));
		sessionBean.reinitializeCategories();
	}
	
	public void remove() {
		categoryService.remove(category);
		setCategories(categoryService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "category removed!", null));
		sessionBean.reinitializeCategories();
	}
	
	public void clear() {
		category = new Category();
	}

}
