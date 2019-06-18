package nl.ckramer.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.ckramer.webshop.entity.AuthUser;

public interface AuthUserDao extends JpaRepository<AuthUser, Integer>{
	
		@Query("SELECT au FROM AuthUser au WHERE au.username = ?1")
		public AuthUser findByUsername(String username);
}
