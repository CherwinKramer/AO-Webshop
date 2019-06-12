package nl.ckramer.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.ProductDao;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public List<Product> findByCategory(Category category) {
		return productDao.findByCategory(category);
	}
	
	public List<Product> findAll() {
		return productDao.findAll();
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public void remove(Product product) {
		productDao.delete(product);
	}

}
