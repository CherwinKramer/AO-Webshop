package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.CategoryDao;
import nl.ckramer.webshop.dao.ProductDao;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Product;
import nl.ckramer.webshop.service.SelectItemService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "productBean")
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private SelectItemService selectItemService;

	private List<Product> products;
	private List<SelectItem> categorySelectList;

	private Product product = new Product();

	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
	    products = productDao.findAll();
	}
	
	public void initializeProductList(String id) {
		products = productDao.findByCategory(Long.valueOf(id));
	}

	public void save() {
		productDao.save(product);
		setProducts(productDao.findAll());
	}

	public void remove(Product product) {
		productDao.delete(product);
		setProducts(productDao.findAll());
	}

	public void add(ActionEvent event) {
		product = new Product();
	}

	public List<SelectItem> getCategorySelectList() {
		if(categorySelectList == null) {
			List<SelectItem> list = new ArrayList<>();
			List<Category> categories = categoryDao.findAll();
			for (Category category : categories) {
				list.add(new SelectItem(category, category.getName()));
			}
			return list;
		}
		return categorySelectList;
	}

	public void setCategorySelectList(List<SelectItem> categorySelectList) {
		this.categorySelectList = categorySelectList;
	}

}
