package nl.ckramer.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.ckramer.webshop.entity.Country;

public interface CountryDao extends JpaRepository<Country, Integer>{

}
