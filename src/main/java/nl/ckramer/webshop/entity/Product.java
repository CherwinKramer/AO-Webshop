package nl.ckramer.webshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
//@Table(name = "product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PRICE", scale = 2)
	private Double price;
	
	@Override
    public int hashCode() {
          return new HashCodeBuilder()
                 .append(getId())
                 .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
          if (obj instanceof Product) {
        	  Product other = (Product) obj;
                 return new EqualsBuilder()
                        .append(getId(), other.getId())
                        .isEquals();
          }
          return false;
    }	
}
