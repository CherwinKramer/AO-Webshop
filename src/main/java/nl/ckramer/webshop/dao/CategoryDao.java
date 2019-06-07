package nl.ckramer.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.ckramer.webshop.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
