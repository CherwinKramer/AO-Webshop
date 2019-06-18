package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;
import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.entity.AuthUser;
import nl.ckramer.webshop.entity.PurchaseOrder;
import nl.ckramer.webshop.service.PurchaseOrderService;
import nl.ckramer.webshop.util.AutowireHelper;

@Getter @Setter
@ViewScoped
@ManagedBean(name = "orderBean")
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@Autowired
	private AuthUserDao authUserDao;

	private List<PurchaseOrder> purchaseOrders;

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	@PostConstruct
	public void initialize() {
		AutowireHelper.autowire(this);
		AuthUser authUser = authUserDao.findByUsername(authentication.getName());
		purchaseOrders = purchaseOrderService.findByAuthUser(authUser);
	}

	public void finalizeOrder(PurchaseOrder order) {
		order.setProcessed(true);
		purchaseOrderService.save(order);
	}

}

