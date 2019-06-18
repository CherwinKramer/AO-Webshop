package nl.ckramer.webshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.Location;

public interface LocationDao extends JpaRepository<Location, Integer>{

	@Query("SELECT l FROM Location l WHERE l.authUser = ?1")
	public List<Location> findByAuthUser(AuthUser authUser);
	
}
