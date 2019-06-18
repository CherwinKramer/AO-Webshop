package nl.ckramer.webshop.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.dao.CategoryDao;
import nl.ckramer.webshop.dao.PurchaseOrderDao;
import nl.ckramer.webshop.dao.PurchaseOrderDetailDao;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.Category;
import nl.ckramer.webshop.entity.Product;
import nl.ckramer.webshop.entity.PurchaseOrder;
import nl.ckramer.webshop.entity.PurchaseOrderDetail;
import nl.ckramer.webshop.service.SelectItemService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@SessionScoped
@ManagedBean(name = "sessionBean")
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private AuthUserDao authUserDao;
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	private PurchaseOrderDetailDao purchaseOrderDetailDao;

	
	@Autowired
	private SelectItemService selectItemService;
	
	private List<SelectItem> locations = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();
	private List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
	
	private Double totalCosts = 0D;
	private AuthUser authUser;
	private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
	private PurchaseOrder purchaseOrder = new PurchaseOrder();
	
	@PostConstruct
	public void init() {
		AutowireHelper.autowire(this);
		if(categories.isEmpty()) {
			categories = categoryDao.findAll();
		}
		
		// Block which only gets loaded if user is logged in.
		if(authUser == null) {
			authUser = authUserDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			if(authUser != null) {
				if(locations.isEmpty()) {
					initializeLocations();
				}
				totalCosts = calculateTotalCost();
			}
		}
	}
	
	public void initializeLocations() {
		locations = selectItemService.loadLocationList(authUser);
	}
	
	public void setProductOnPurchaseOrderDetail(Product product) {
		purchaseOrderDetail.setProduct(product);
	}
	
	public void savePurchaseOrderDetail() {
		addPurchaseOrderDetailToList(purchaseOrderDetail);
		purchaseOrderDetail = new PurchaseOrderDetail();
	}
	
	public void reinitializeCategories() {
		categories = categoryDao.findAll();
	}

	public void deletePurchaseOrderDetail(PurchaseOrderDetail pod) {
		purchaseOrderDetails.remove(pod);
	}
	
	private void addPurchaseOrderDetailToList(PurchaseOrderDetail pod) {
		for(PurchaseOrderDetail detail : purchaseOrderDetails) {
			if(detail.getProduct().equals(pod.getProduct())) {
				detail.setQuantity(pod.getQuantity() + detail.getQuantity());
				return;
			}
		}
		purchaseOrderDetails.add(pod);
	}
	
	private double calculateTotalCost() {
		double costs = 0D;
		for(PurchaseOrderDetail pod : purchaseOrderDetails) {
			costs = costs + (pod.getQuantity() * pod.getProduct().getPrice());
		}
		return costs;
	}
	
	public void onValueChangeListener(PurchaseOrderDetail pod) {
		if(pod.getQuantity() == 0L) {
			purchaseOrderDetails.remove(pod);
		}
		totalCosts = calculateTotalCost();
	}
	
	public void finalizeOrder() throws IOException {
		purchaseOrder.setAuthUser(authUser);
		PurchaseOrder po = purchaseOrderDao.save(purchaseOrder);
		for(PurchaseOrderDetail pod : purchaseOrderDetails) {
			pod.setPurchaseOrder(po);
		}
		purchaseOrderDetailDao.save(purchaseOrderDetails);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect(externalContext.getRequestContextPath() + "/pages/account/orders.xhtml");
		
		resetValues();
	}
	
	private void resetValues() {
		purchaseOrder = new PurchaseOrder();
		purchaseOrderDetails = new ArrayList<>();
		authUser = null;
		locations = new ArrayList<>();
		totalCosts = 0D;
	}
}
