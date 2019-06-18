package nl.ckramer.webshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.PurchaseOrder;

public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Integer>{
	
	@Query("SELECT po FROM PurchaseOrder po WHERE po.authUser = ?1")
	public List<PurchaseOrder> findByAuthUser(AuthUser authUser);
	
}

