package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.service.CategoryService;

@Getter @Setter
@Named
@ViewScoped
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService categoryService;
	
	private List<Category> categories;
	
	private Category category = new Category();
	
	@PostConstruct
	public void init() {
		categories = categoryService.findAll();
	}
	
	public void save() {
		categoryService.save(category);
		category = new Category();
		setCategories(categoryService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "category saved!", null));
	}
	
	public void remove(Category category) {
		categoryService.remove(category);
		setCategories(categoryService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "category removed!", null));
	}
	
	public void clear() {
		category = new Category();
	}

}
