package nl.ckramer.webshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
//@Table(name = "order_detail")
public class PurchaseOrderDetail {

	@Id
	@Column(name = "ORDER_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PURCHASE_ORDER_ID")
	private PurchaseOrder purchaseOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@Column(name = "QUANTITY")
	private Long quantity;
	
}
