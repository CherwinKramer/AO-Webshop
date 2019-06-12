package nl.ckramer.webshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
//@Table(name = "order")
public class PurchaseOrder {

	@Id
	@Column(name = "PURCHASE_ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private AuthUser authUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCATION_ID")
	private Location location;
	
	@Column(name = "PROCESSED_YN")
	private boolean processed;
	
	@Column(name = "TRACK_AND_TRACE")
	private String trackAndTrace;

}
