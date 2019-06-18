package nl.ckramer.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.ckramer.webshop.entity.AuthRole;

public interface AuthRoleDao extends JpaRepository<AuthRole, Integer>{
	
//		@Query("SELECT ar FROM AuthRole ar WHERE ar.authUser = ?1")
//		public List<AuthRole> findByAuthUser(AuthUser authUser);
}
