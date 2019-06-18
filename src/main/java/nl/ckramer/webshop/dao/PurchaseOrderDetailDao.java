package nl.ckramer.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.ckramer.webshop.entity.PurchaseOrderDetail;

public interface PurchaseOrderDetailDao extends JpaRepository<PurchaseOrderDetail, Integer>{
	
}

