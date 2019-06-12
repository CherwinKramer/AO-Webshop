package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.CategoryDao;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.PurchaseOrder;
import nl.ckramer.webshop.entity.PurchaseOrderDetail;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@SessionScoped
@ManagedBean(name = "sessionBean")
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryDao categoryDao;
	
	private List<Category> categories;
	private List<PurchaseOrderDetail> purchaseOrderDetails;
	
	private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
	private PurchaseOrder purchaseOrder = new PurchaseOrder();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		categories = categoryDao.findAll();
	}
	
	public void reinitializeCategories() {
		categories = categoryDao.findAll();
	}

}
