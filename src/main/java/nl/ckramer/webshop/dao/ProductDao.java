package nl.ckramer.webshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category = ?1")
	public List<Product> findByCategory(Category category);
	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	public List<Product> findByCategory(Long id);
}
	
