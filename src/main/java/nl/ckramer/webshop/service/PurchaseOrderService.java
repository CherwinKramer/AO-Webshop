package nl.ckramer.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.PurchaseOrderDao;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Product;
import nl.ckramer.webshop.entity.PurchaseOrder;

@Service
public class PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	
	public List<PurchaseOrder> findByAuthUser(AuthUser authUser) {
		if(authUser.getRole().getRole().equals("USER")) {
			return purchaseOrderDao.findByAuthUser(authUser);
		} else {
			return findAll();
		}
	}
	
	public List<PurchaseOrder> findAll() {
		return purchaseOrderDao.findAll();
	}

	public void save(PurchaseOrder purchaseOrder) {
		purchaseOrderDao.save(purchaseOrder);
	}

	public void remove(PurchaseOrder purchaseOrder) {
		purchaseOrderDao.delete(purchaseOrder);
	}

}
