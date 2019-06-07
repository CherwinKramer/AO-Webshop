package nl.ckramer.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.CategoryDao;
import nl.ckramer.webshop.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public void save(Category category) {
		categoryDao.save(category);
	}

	public void remove(Category category) {
		categoryDao.delete(category);
	}

}
