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
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Product;
import nl.ckramer.webshop.service.CategoryService;
import nl.ckramer.webshop.service.ProductService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "productBean")
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	private List<Product> products;
	
	private Product product = new Product();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		products = productService.findAll();
	}
	
	public void initializeProductList(Category category) {
		products = productService.findByCategory(category);
	}
	
	public void save() {
		productService.save(product);
		product = new Product();
		setProducts(productService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "product saved!", null));
	}
	
	public void remove(Product product) {
		productService.remove(product);
		setProducts(productService.findAll());
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "product removed!", null));
	}
	
	public void clear() {
		product = new Product();
	}

}
